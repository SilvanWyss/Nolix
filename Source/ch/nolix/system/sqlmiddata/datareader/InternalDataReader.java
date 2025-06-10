package ch.nolix.system.sqlmiddata.datareader;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.sql.connection.SqlConnection;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.sqlapi.connectionapi.ISqlConnection;
import ch.nolix.system.middata.valuemapper.ValueMapper;
import ch.nolix.system.sqlmiddata.modelmapper.LoadedEntityDtoMapper;
import ch.nolix.system.sqlmiddata.querycreator.EntityQueryCreator;
import ch.nolix.system.sqlmiddata.querycreator.MultiBackReferenceQueryCreator;
import ch.nolix.system.sqlmiddata.querycreator.MultiReferenceQueryCreator;
import ch.nolix.system.sqlmiddata.querycreator.MultiValueQueryCreator;
import ch.nolix.system.time.moment.Time;
import ch.nolix.systemapi.middataapi.midschemaview.ColumnViewDto;
import ch.nolix.systemapi.middataapi.midschemaview.TableViewDto;
import ch.nolix.systemapi.middataapi.modelapi.EntityLoadingDto;
import ch.nolix.systemapi.middataapi.modelapi.MultiReferenceEntryDto;
import ch.nolix.systemapi.middataapi.valuemapperapi.IValueMapper;
import ch.nolix.systemapi.sqlmiddataapi.querycreatorapi.IEntityQueryCreator;
import ch.nolix.systemapi.sqlmiddataapi.querycreatorapi.IMultiBackReferenceQueryCreator;
import ch.nolix.systemapi.sqlmiddataapi.querycreatorapi.IMultiReferenceQueryCreator;
import ch.nolix.systemapi.sqlmiddataapi.querycreatorapi.IMultiValueQueryCreator;

final class InternalDataReader {

  private static final IEntityQueryCreator ENTITY_QUERY_CREATOR = new EntityQueryCreator();

  private static final IMultiValueQueryCreator MULTI_VALUE_QUERY_CREATOR = new MultiValueQueryCreator();

  private static final IMultiReferenceQueryCreator MULTI_REFERENCE_QUERY_CREATOR = new MultiReferenceQueryCreator();

  private static final IMultiBackReferenceQueryCreator MULTI_BACK_REFERENCE_QUERY_CREATOR = //
  new MultiBackReferenceQueryCreator();

  private static final LoadedEntityDtoMapper LOADED_ENTITY_DTO_MAPPER = new LoadedEntityDtoMapper();

  private static final IValueMapper VALUE_MAPPER = new ValueMapper();

  private final String databaseName;

  private final ISqlConnection sqlConnection;

  public InternalDataReader(
    final String databaseName,
    final ISqlConnection sqlConnection) {

    Validator.assertThat(databaseName).thatIsNamed("database name").isNotBlank();
    Validator.assertThat(sqlConnection).thatIsNamed(SqlConnection.class).isNotNull();

    this.databaseName = databaseName;
    this.sqlConnection = sqlConnection;

    sqlConnection.executeStatement("USE " + databaseName);
  }

  public String getDatabaseName() {
    return databaseName;
  }

  public Time getSchemaTimestamp() {
    return Time.fromString(
      sqlConnection
        .getSingleRecordFromQuery(ENTITY_QUERY_CREATOR.createQueryToLoadSchemaTimestamp())
        .getStoredAtOneBasedIndex(1));
  }

  public IContainer<String> loadMultiBackReferenceEntries(
    final String entityId,
    final ColumnViewDto multiBackReferenceColumnInfo) {

    final var query = MULTI_BACK_REFERENCE_QUERY_CREATOR.createQueryToLoadMultiBackReferenceEntries(
      entityId,
      multiBackReferenceColumnInfo.id());

    return sqlConnection.getRecordsFromQuery(query).to(r -> r.getStoredAtOneBasedIndex(1));
  }

  public IContainer<MultiReferenceEntryDto> loadMultiReferenceEntries(
    final String entityId,
    final ColumnViewDto multiReferenceColumnInfo) {

    final var query = //
    MULTI_REFERENCE_QUERY_CREATOR.createQueryToLoadMultiReferenceEntries(entityId, multiReferenceColumnInfo.id());

    final var sqlRecords = sqlConnection.getRecordsFromQuery(query);

    //TODO: Create MultiReferenceEntryDtoMapper
    return //
    sqlRecords.to(r -> new MultiReferenceEntryDto(null, entityId, multiReferenceColumnInfo.id(),
      r.getStoredAtOneBasedIndex(1), null));
  }

  public IContainer<Object> loadMultiValueEntries(
    final String entityId,
    final ColumnViewDto multiValueColumnInfo) {
    return sqlConnection
      .getRecordsFromQuery(
        MULTI_VALUE_QUERY_CREATOR.createQueryToLoadMultiValueEntries(
          entityId,
          multiValueColumnInfo.id()))
      .to(r -> VALUE_MAPPER.mapStringToValue(r.getStoredAtOneBasedIndex(1), multiValueColumnInfo.dataType()));
  }

  public IContainer<EntityLoadingDto> loadEntitiesOfTable(final TableViewDto tableView) {

    final var query = ENTITY_QUERY_CREATOR.createQueryToLoadEntitiesOfTable(tableView);
    final var records = sqlConnection.getRecordsFromQuery(query);

    return records.to(r -> LOADED_ENTITY_DTO_MAPPER.mapSqlRecordToEntityLoadingDto(r, tableView));
  }

  public EntityLoadingDto loadEntity(final TableViewDto tableView, final String id) {

    final var query = ENTITY_QUERY_CREATOR.createQueryToLoadEntity(id, tableView);
    final var sqlRecord = sqlConnection.getSingleRecordFromQuery(query);

    return LOADED_ENTITY_DTO_MAPPER.mapSqlRecordToEntityLoadingDto(sqlRecord, tableView);
  }

  public boolean tableContainsEntityWithGivenValueAtGivenColumn(
    final String tableName,
    final ColumnViewDto columnInfo,
    final String value) {

    final var contentType = columnInfo.contentType();

    return //
    switch (contentType) {
      case VALUE, OPTIONAL_VALUE, REFERENCE, OPTIONAL_REFERENCE, BACK_REFERENCE, OPTIONAL_BACK_REFERENCE ->
        tableContainsEntityWithGivenValueAtGivenSingleColumn(
          tableName,
          columnInfo.name(),
          value);
      case MULTI_VALUE ->
        multiValueEntryExistsForGivenColumnAndValue(columnInfo.id(), value);
      case MULTI_REFERENCE ->
        multiReferenceEntryExistsForGivenColumnAndReferencedEntity(columnInfo.id(), value);
      default ->
        throw InvalidArgumentException.forArgument(contentType);
    };
  }

  public boolean tableContainsEntityWithGivenValueAtGivenColumnIgnoringGivenEntities(
    final String tableName,
    final ColumnViewDto columnInfo,
    final String value,
    final IContainer<String> entitiesToIgnoreIds) {

    final var contentType = columnInfo.contentType();

    return //
    switch (contentType) {
      case VALUE, OPTIONAL_VALUE, REFERENCE, OPTIONAL_REFERENCE, BACK_REFERENCE, OPTIONAL_BACK_REFERENCE ->
        tableContainsEntityWithGivenValueAtGivenSingleColumnIgnoringGivenEntities(
          tableName,
          columnInfo.name(),
          value,
          entitiesToIgnoreIds);
      case MULTI_VALUE ->
        multiValueEntryExistsForGivenColumnAndValueIgnoringGivenEntities(
          columnInfo.id(),
          value,
          entitiesToIgnoreIds);
      case MULTI_REFERENCE ->
        multiReferenceEntryExistsForGivenColumnAndReferencedEntityIgnoringGivenEntities(
          columnInfo.id(),
          value,
          entitiesToIgnoreIds);
      default ->
        throw InvalidArgumentException.forArgument(contentType);
    };
  }

  public boolean tableContainsEntityWithGivenId(final String tableName, final String id) {

    final var entityCount = Integer.valueOf(
      sqlConnection
        .getSingleRecordFromQuery(ENTITY_QUERY_CREATOR.createQueryToCountEntitiesWithGivenId(tableName, id))
        .getStoredAtOneBasedIndex(1));

    return entityCount > 0;
  }

  private boolean multiReferenceEntryExistsForGivenColumnAndReferencedEntity(
    final String columnId,
    final String referencedEntityId) {
    return sqlConnection.getRecordsFromQuery(
      MULTI_REFERENCE_QUERY_CREATOR
        .createQueryToLoadOptionalFirstMultiReferenceEntry(
          columnId,
          referencedEntityId))
      .containsAny();
  }

  private boolean multiReferenceEntryExistsForGivenColumnAndReferencedEntityIgnoringGivenEntities(
    final String columnId,
    final String referencedEntityId,
    final IContainer<String> entitiesToIgnoreIds) {

    final var query = //
    MULTI_REFERENCE_QUERY_CREATOR
      .createQueryToCountMultiReferenceEntriesForGivenColumnAndReferencedEntityIgnoringGivenEntities(
        columnId,
        referencedEntityId,
        entitiesToIgnoreIds);

    final var localRecord = sqlConnection.getSingleRecordFromQuery(query);
    final var entityCount = Integer.valueOf(localRecord.getStoredFirst());

    return (entityCount > 0);
  }

  private boolean multiValueEntryExistsForGivenColumnAndValue(
    final String columnId,
    final String value) {
    return sqlConnection.getRecordsFromQuery(
      MULTI_VALUE_QUERY_CREATOR.createQueryToLoadOneOrNoneMultiValueEntryForGivenColumnAndValue(
        columnId,
        value))
      .containsAny();
  }

  private boolean multiValueEntryExistsForGivenColumnAndValueIgnoringGivenEntities(
    final String columnId,
    final String value,
    final IContainer<String> entitiesToIgnoreIds) {

    final var query = //
    MULTI_VALUE_QUERY_CREATOR.createQueryToCountMultiValueEntriesForGivenColumnAndValueIgnoringGivenEntities(
      columnId,
      value,
      entitiesToIgnoreIds);

    final var localRecord = sqlConnection.getSingleRecordFromQuery(query);
    final var entityCount = Integer.valueOf(localRecord.getStoredFirst());

    return entityCount > 0;
  }

  private boolean tableContainsEntityWithGivenValueAtGivenSingleColumn(
    final String tableName,
    final String singleColumnName,
    final String value) {
    return Integer.valueOf(
      sqlConnection.getSingleRecordFromQuery(
        ENTITY_QUERY_CREATOR.createQueryToCountEntitiesWithGivenValueAtGivenColumn(
          tableName,
          singleColumnName,
          value))
        .getStoredAtOneBasedIndex(1)) > 0;
  }

  private boolean tableContainsEntityWithGivenValueAtGivenSingleColumnIgnoringGivenEntities(
    final String tableName,
    final String columnName,
    final String value,
    final IContainer<String> entitiesToIgnoreIds) {

    final var query = //
    ENTITY_QUERY_CREATOR.createQueryToCountEntitiesWithGivenValueAtGivenColumnIgnoringGivenEntities(
      tableName,
      columnName,
      value,
      entitiesToIgnoreIds);

    final var localRecord = sqlConnection.getSingleRecordFromQuery(query);
    final var entityCount = Integer.valueOf(localRecord.getStoredFirst());

    return entityCount > 0;
  }
}
