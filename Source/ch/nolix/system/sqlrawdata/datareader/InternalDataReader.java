package ch.nolix.system.sqlrawdata.datareader;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.sql.connection.SqlConnection;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.sqlapi.connectionapi.ISqlConnection;
import ch.nolix.system.time.moment.Time;
import ch.nolix.systemapi.rawdataapi.datadto.EntityLoadingDto;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.IColumnInfo;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.ITableInfo;
import ch.nolix.systemapi.sqlrawdataapi.querycreatorapi.IEntityQueryCreator;
import ch.nolix.systemapi.sqlrawdataapi.querycreatorapi.IMultiBackReferenceQueryCreator;
import ch.nolix.systemapi.sqlrawdataapi.querycreatorapi.IMultiReferenceQueryCreator;
import ch.nolix.systemapi.sqlrawdataapi.querycreatorapi.IMultiValueQueryCreator;
import ch.nolix.systemapi.sqlrawdataapi.sqlsyntaxapi.ISqlSyntaxProvider;

final class InternalDataReader {

  private static final LoadedEntityDtoMapper LOADED_ENTITY_DTO_MAPPER = new LoadedEntityDtoMapper();

  private static final ValueMapper VALUE_MAPPER = new ValueMapper();

  private final ISqlConnection sqlConnection;

  private final IEntityQueryCreator entityQueryCreator;

  private final IMultiValueQueryCreator multiValueQueryCreator;

  private final IMultiReferenceQueryCreator multiReferenceQueryCreator;

  private final IMultiBackReferenceQueryCreator multiBackReferenceQueryCreator;

  public InternalDataReader(
    final String databaseName,
    final ISqlConnection sqlConnection,
    final ISqlSyntaxProvider sqlSyntaxProvider) {

    GlobalValidator.assertThat(sqlConnection).thatIsNamed(SqlConnection.class).isNotNull();

    this.sqlConnection = sqlConnection;
    entityQueryCreator = sqlSyntaxProvider.getEntityQueryCreator();
    multiValueQueryCreator = sqlSyntaxProvider.getMultiValueQueryCreator();
    multiReferenceQueryCreator = sqlSyntaxProvider.getMultiReferenceQueryCreator();
    multiBackReferenceQueryCreator = sqlSyntaxProvider.getMultiBackReferenceQueryCreator();

    sqlConnection.executeStatement("USE " + databaseName);
  }

  public Time getSchemaTimestamp() {
    return Time.fromString(
      sqlConnection
        .getSingleRecordFromQuery(entityQueryCreator.createQueryToLoadSchemaTimestamp())
        .getStoredAt1BasedIndex(1));
  }

  public IContainer<String> loadMultiBackReferenceEntries(
    final String entityId,
    final IColumnInfo multiBackReferenceColumnInfo) {

    final var query = multiBackReferenceQueryCreator.createQueryToLoadMultiBackReferenceEntries(
      entityId,
      multiBackReferenceColumnInfo.getColumnId());

    return sqlConnection.getRecordsFromQuery(query).to(r -> r.getStoredAt1BasedIndex(1));
  }

  public IContainer<String> loadMultiReferenceEntries(
    final String entityId,
    final IColumnInfo multiReferenceColumnInfo) {
    return sqlConnection
      .getRecordsFromQuery(
        multiReferenceQueryCreator.createQueryToLoadMultiReferenceEntries(
          entityId,
          multiReferenceColumnInfo.getColumnId()))
      .to(r -> r.getStoredAt1BasedIndex(1));
  }

  public IContainer<Object> loadMultiValueEntries(
    final String entityId,
    final IColumnInfo multiValueColumnInfo) {
    return sqlConnection
      .getRecordsFromQuery(
        multiValueQueryCreator.createQueryToLoadMultiValueEntries(
          entityId,
          multiValueColumnInfo.getColumnId()))
      .to(r -> VALUE_MAPPER.createValueFromString(r.getStoredAt1BasedIndex(1), multiValueColumnInfo));
  }

  public IContainer<EntityLoadingDto> loadEntitiesOfTable(final ITableInfo tableInfo) {
    return sqlConnection
      .getRecordsFromQuery(entityQueryCreator.createQueryToLoadEntitiesOfTable(tableInfo))
      .to(r -> LOADED_ENTITY_DTO_MAPPER.createLoadedEntityDtoFrosqlRecord(r, tableInfo));
  }

  public EntityLoadingDto loadEntity(final ITableInfo tableInfo, final String id) {
    return LOADED_ENTITY_DTO_MAPPER.createLoadedEntityDtoFrosqlRecord(
      sqlConnection.getSingleRecordFromQuery(entityQueryCreator.createQueryToLoadEntity(id, tableInfo)),
      tableInfo);
  }

  public boolean tableContainsEntityWithGivenValueAtGivenColumn(
    final String tableName,
    final IColumnInfo columnInfo,
    final String value) {

    final var fieldType = columnInfo.getColumnFieldType();

    return //
    switch (fieldType) {
      case VALUE, OPTIONAL_VALUE, REFERENCE, OPTIONAL_REFERENCE, BACK_REFERENCE, OPTIONAL_BACK_REFERENCE ->
        tableContainsEntityWithGivenValueAtGivenSingleColumn(
          tableName,
          columnInfo.getColumnName(),
          value);
      case MULTI_VALUE ->
        multiValueEntryExistsForGivenColumnAndValue(columnInfo.getColumnId(), value);
      case MULTI_REFERENCE ->
        multiReferenceEntryExistsForGivenColumnAndReferencedEntity(columnInfo.getColumnId(), value);
      default ->
        throw InvalidArgumentException.forArgument(columnInfo.getColumnFieldType());
    };
  }

  public boolean tableContainsEntityWithGivenValueAtGivenColumnIgnoringGivenEntities(
    final String tableName,
    final IColumnInfo columnInfo,
    final String value,
    final IContainer<String> entitiesToIgnoreIds) {

    final var fieldType = columnInfo.getColumnFieldType();

    return //
    switch (fieldType) {
      case VALUE, OPTIONAL_VALUE, REFERENCE, OPTIONAL_REFERENCE, BACK_REFERENCE, OPTIONAL_BACK_REFERENCE ->
        tableContainsEntityWithGivenValueAtGivenSingleColumnIgnoringGivenEntities(
          tableName,
          columnInfo.getColumnName(),
          value,
          entitiesToIgnoreIds);
      case MULTI_VALUE ->
        multiValueEntryExistsForGivenColumnAndValueIgnoringGivenEntities(
          columnInfo.getColumnId(),
          value,
          entitiesToIgnoreIds);
      case MULTI_REFERENCE ->
        multiReferenceEntryExistsForGivenColumnAndReferencedEntityIgnoringGivenEntities(
          columnInfo.getColumnId(),
          value,
          entitiesToIgnoreIds);
      default ->
        throw InvalidArgumentException.forArgument(columnInfo.getColumnFieldType());
    };
  }

  public boolean tableContainsEntityWithGivenId(final String tableName, final String id) {

    final var entityCount = Integer.valueOf(
      sqlConnection
        .getSingleRecordFromQuery(entityQueryCreator.createQueryToCountEntitiesWithGivenId(tableName, id))
        .getStoredAt1BasedIndex(1));

    return entityCount > 0;
  }

  private boolean multiReferenceEntryExistsForGivenColumnAndReferencedEntity(
    final String columnId,
    final String referencedEntityId) {
    return sqlConnection.getRecordsFromQuery(
      multiReferenceQueryCreator
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
    multiReferenceQueryCreator
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
      multiValueQueryCreator.createQueryToLoadOneOrNoneMultiValueEntryForGivenColumnAndValue(
        columnId,
        value))
      .containsAny();
  }

  private boolean multiValueEntryExistsForGivenColumnAndValueIgnoringGivenEntities(
    final String columnId,
    final String value,
    final IContainer<String> entitiesToIgnoreIds) {

    final var query = //
    multiValueQueryCreator.createQueryToCountMultiValueEntriesForGivenColumnAndValueIgnoringGivenEntities(
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
        entityQueryCreator.createQueryToCountEntitiesWithGivenValueAtGivenColumn(
          tableName,
          singleColumnName,
          value))
        .getStoredAt1BasedIndex(1)) > 0;
  }

  private boolean tableContainsEntityWithGivenValueAtGivenSingleColumnIgnoringGivenEntities(
    final String tableName,
    final String columnName,
    final String value,
    final IContainer<String> entitiesToIgnoreIds) {

    final var query = //
    entityQueryCreator.createQueryToCountEntitiesWithGivenValueAtGivenColumnIgnoringGivenEntities(
      tableName,
      columnName,
      value,
      entitiesToIgnoreIds);

    final var localRecord = sqlConnection.getSingleRecordFromQuery(query);
    final var entityCount = Integer.valueOf(localRecord.getStoredFirst());

    return entityCount > 0;
  }
}
