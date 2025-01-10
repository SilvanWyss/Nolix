package ch.nolix.system.sqlrawdata.datareader;

import ch.nolix.core.container.arraylist.ArrayList;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.sql.connection.SqlConnection;
import ch.nolix.core.sql.model.Record;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.IArrayList;
import ch.nolix.coreapi.sqlapi.connectionapi.ISqlConnection;
import ch.nolix.system.sqlrawdata.datamapper.ValueMapper;
import ch.nolix.system.sqlrawdata.querycreator.EntityQueryCreator;
import ch.nolix.system.sqlrawdata.querycreator.MultiBackReferenceQueryCreator;
import ch.nolix.system.sqlrawdata.querycreator.MultiReferenceQueryCreator;
import ch.nolix.system.sqlrawdata.querycreator.MultiValueQueryCreator;
import ch.nolix.system.sqlrawdata.rawdatadtomapper.LoadedEntityDtoMapper;
import ch.nolix.system.time.moment.Time;
import ch.nolix.systemapi.rawdataapi.dto.EntityLoadingDto;
import ch.nolix.systemapi.rawdataapi.schemaviewapi.IColumnView;
import ch.nolix.systemapi.rawdataapi.schemaviewapi.ITableView;
import ch.nolix.systemapi.sqlrawdataapi.datamapperapi.IValueMapper;
import ch.nolix.systemapi.sqlrawdataapi.querycreatorapi.IEntityQueryCreator;
import ch.nolix.systemapi.sqlrawdataapi.querycreatorapi.IMultiBackReferenceQueryCreator;
import ch.nolix.systemapi.sqlrawdataapi.querycreatorapi.IMultiReferenceQueryCreator;
import ch.nolix.systemapi.sqlrawdataapi.querycreatorapi.IMultiValueQueryCreator;

final class InternalDataReader {

  private static final IEntityQueryCreator ENTITY_QUERY_CREATOR = new EntityQueryCreator();

  private static final IMultiValueQueryCreator MULTI_VALUE_QUERY_CREATOR = new MultiValueQueryCreator();

  private static final IMultiReferenceQueryCreator MULTI_REFERENCE_QUERY_CREATOR = new MultiReferenceQueryCreator();

  private static final IMultiBackReferenceQueryCreator MULTI_BACK_REFERENCE_QUERY_CREATOR = //
  new MultiBackReferenceQueryCreator();

  private static final LoadedEntityDtoMapper LOADED_ENTITY_DTO_MAPPER = new LoadedEntityDtoMapper();

  private static final IValueMapper VALUE_MAPPER = new ValueMapper();

  private final ISqlConnection sqlConnection;

  public InternalDataReader(
    final String databaseName,
    final ISqlConnection sqlConnection) {

    GlobalValidator.assertThat(sqlConnection).thatIsNamed(SqlConnection.class).isNotNull();

    this.sqlConnection = sqlConnection;

    sqlConnection.executeStatement("USE " + databaseName);
  }

  public Time getSchemaTimestamp() {
    return Time.fromString(
      sqlConnection
        .getSingleRecordFromQuery(ENTITY_QUERY_CREATOR.createQueryToLoadSchemaTimestamp())
        .getStoredAt1BasedIndex(1));
  }

  public IContainer<String> loadMultiBackReferenceEntries(
    final String entityId,
    final IColumnView multiBackReferenceColumnInfo) {

    final var query = MULTI_BACK_REFERENCE_QUERY_CREATOR.createQueryToLoadMultiBackReferenceEntries(
      entityId,
      multiBackReferenceColumnInfo.getColumnId());

    return sqlConnection.getRecordsFromQuery(query).to(r -> r.getStoredAt1BasedIndex(1));
  }

  public IContainer<String> loadMultiReferenceEntries(
    final String entityId,
    final IColumnView multiReferenceColumnInfo) {
    return sqlConnection
      .getRecordsFromQuery(
        MULTI_REFERENCE_QUERY_CREATOR.createQueryToLoadMultiReferenceEntries(
          entityId,
          multiReferenceColumnInfo.getColumnId()))
      .to(r -> r.getStoredAt1BasedIndex(1));
  }

  public IContainer<Object> loadMultiValueEntries(
    final String entityId,
    final IColumnView multiValueColumnInfo) {
    return sqlConnection
      .getRecordsFromQuery(
        MULTI_VALUE_QUERY_CREATOR.createQueryToLoadMultiValueEntries(
          entityId,
          multiValueColumnInfo.getColumnId()))
      .to(r -> VALUE_MAPPER.mapValueToString(r.getStoredAt1BasedIndex(1), multiValueColumnInfo.getColumnDataType()));
  }

  public IContainer<EntityLoadingDto> loadEntitiesOfTable(final ITableView tableView) {

    //TODO: Create EntityLoadingDtoMapper
    final var query = ENTITY_QUERY_CREATOR.createQueryToLoadEntitiesOfTable(tableView);
    final var records = sqlConnection.getRecordsFromQuery(query);

    //TODO: Add withInitialCapacityFromCountOfContainer static method to ArrayList
    final IArrayList<EntityLoadingDto> entities = ArrayList.withInitialCapacity(records.getCount());

    var index = 1;

    //TODO Add toUsingOneBasedIndex method to IContainer
    for (final var r : records) {

      final var entity = //
      LOADED_ENTITY_DTO_MAPPER.mapRecordToEntityLoadingDto(Record.withOneBasedIndexAndValues(index, r), tableView);

      entities.addAtEnd(entity);
    }

    return entities;
  }

  public EntityLoadingDto loadEntity(final ITableView tableView, final String id) {

    //TODO: Create EntityLoadingDtoMapper
    return LOADED_ENTITY_DTO_MAPPER.mapRecordToEntityLoadingDto(
      Record.withOneBasedIndexAndValues(1,
        sqlConnection.getSingleRecordFromQuery(ENTITY_QUERY_CREATOR.createQueryToLoadEntity(id, tableView))),
      tableView);
  }

  public boolean tableContainsEntityWithGivenValueAtGivenColumn(
    final String tableName,
    final IColumnView columnInfo,
    final String value) {

    final var contentType = columnInfo.getColumnContentType();

    return //
    switch (contentType) {
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
        throw InvalidArgumentException.forArgument(contentType);
    };
  }

  public boolean tableContainsEntityWithGivenValueAtGivenColumnIgnoringGivenEntities(
    final String tableName,
    final IColumnView columnInfo,
    final String value,
    final IContainer<String> entitiesToIgnoreIds) {

    final var contentType = columnInfo.getColumnContentType();

    return //
    switch (contentType) {
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
        throw InvalidArgumentException.forArgument(contentType);
    };
  }

  public boolean tableContainsEntityWithGivenId(final String tableName, final String id) {

    final var entityCount = Integer.valueOf(
      sqlConnection
        .getSingleRecordFromQuery(ENTITY_QUERY_CREATOR.createQueryToCountEntitiesWithGivenId(tableName, id))
        .getStoredAt1BasedIndex(1));

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
        .getStoredAt1BasedIndex(1)) > 0;
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
