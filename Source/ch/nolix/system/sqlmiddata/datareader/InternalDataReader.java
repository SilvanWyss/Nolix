/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.sqlmiddata.datareader;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.sql.connection.AbstractSqlConnection;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.sql.connection.ISqlConnection;
import ch.nolix.system.middata.valuemapper.ValueMapper;
import ch.nolix.system.sqlmiddata.modelmapper.LoadedEntityDtoMapper;
import ch.nolix.system.sqlmiddata.modelmapper.MultiBackReferenceEntryDtoMapper;
import ch.nolix.system.sqlmiddata.modelmapper.MultiReferenceEntryDtoMapper;
import ch.nolix.system.sqlmiddata.querycreator.EntityQueryCreator;
import ch.nolix.system.sqlmiddata.querycreator.MultiBackReferenceQueryCreator;
import ch.nolix.system.sqlmiddata.querycreator.MultiReferenceQueryCreator;
import ch.nolix.system.sqlmiddata.querycreator.MultiValueQueryCreator;
import ch.nolix.system.time.moment.Time;
import ch.nolix.systemapi.middata.model.EntityLoadingDto;
import ch.nolix.systemapi.middata.model.MultiBackReferenceEntryDto;
import ch.nolix.systemapi.middata.model.MultiReferenceEntryDto;
import ch.nolix.systemapi.middata.valuemapper.IValueMapper;
import ch.nolix.systemapi.midschema.structure.TableIdentification;
import ch.nolix.systemapi.midschemaview.model.ColumnViewDto;
import ch.nolix.systemapi.midschemaview.model.DatabaseViewDto;
import ch.nolix.systemapi.midschemaview.model.TableViewDto;
import ch.nolix.systemapi.sqlmiddata.modelmapper.IMultiBackReferenceEntryDtoMapper;
import ch.nolix.systemapi.sqlmiddata.modelmapper.IMultiReferenceEntryDtoMapper;
import ch.nolix.systemapi.sqlmiddata.querycreator.IEntityQueryCreator;
import ch.nolix.systemapi.sqlmiddata.querycreator.IMultiBackReferenceQueryCreator;
import ch.nolix.systemapi.sqlmiddata.querycreator.IMultiReferenceQueryCreator;
import ch.nolix.systemapi.sqlmiddata.querycreator.IMultiValueQueryCreator;

final class InternalDataReader {
  private static final IEntityQueryCreator ENTITY_QUERY_CREATOR = new EntityQueryCreator();

  private static final IMultiValueQueryCreator MULTI_VALUE_QUERY_CREATOR = new MultiValueQueryCreator();

  private static final IMultiReferenceQueryCreator MULTI_REFERENCE_QUERY_CREATOR = new MultiReferenceQueryCreator();

  private static final IMultiBackReferenceQueryCreator MULTI_BACK_REFERENCE_QUERY_CREATOR = //
  new MultiBackReferenceQueryCreator();

  private static final LoadedEntityDtoMapper LOADED_ENTITY_DTO_MAPPER = new LoadedEntityDtoMapper();

  private static final IMultiReferenceEntryDtoMapper MULTI_REFERENCE_ENTRY_DTO_MAPPER = //
  new MultiReferenceEntryDtoMapper();

  private static final IMultiBackReferenceEntryDtoMapper MULTI_BACK_REFERENCE_ENTRY_DTO_MAPPER = //
  new MultiBackReferenceEntryDtoMapper();

  private static final IValueMapper VALUE_MAPPER = new ValueMapper();

  private final String databaseName;

  private final ISqlConnection sqlConnection;

  public InternalDataReader(
    final String databaseName,
    final ISqlConnection sqlConnection) {
    Validator.assertThat(databaseName).thatIsNamed("database name").isNotBlank();
    Validator.assertThat(sqlConnection).thatIsNamed(AbstractSqlConnection.class).isNotNull();

    this.databaseName = databaseName;
    this.sqlConnection = sqlConnection;

    sqlConnection.executeStatement("USE " + databaseName);
  }

  public String getDatabaseName() {
    return databaseName;
  }

  public int getEntityCount(String tableName) {
    final var query = ENTITY_QUERY_CREATOR.createQueryToCountEntities(tableName);
    final var sqlRecord = sqlConnection.getSingleRecordFromQuery(query);

    return Integer.valueOf(sqlRecord.getStoredOne());
  }

  public Time getSchemaTimestamp() {
    return Time.fromString(
      sqlConnection
        .getSingleRecordFromQuery(ENTITY_QUERY_CREATOR.createQueryToLoadSchemaTimestamp())
        .getStoredAtOneBasedIndex(1));
  }

  public IContainer<MultiBackReferenceEntryDto> loadMultiBackReferenceEntries(
    final TableIdentification table,
    final String entityId,
    final String multiBackReferenceColumnId) {
    final var tableName = table.tableName();

    final var query = //
    MULTI_BACK_REFERENCE_QUERY_CREATOR.createQueryToLoadMultiBackReferenceEntries(entityId, multiBackReferenceColumnId);

    final var sqlRecords = sqlConnection.getRecordsFromQuery(query);

    return //
    MULTI_BACK_REFERENCE_ENTRY_DTO_MAPPER.mapMultiBackReferenceEntrySqlRecordsToMultiBackReferenceEntryDtos(
      sqlRecords,
      tableName);
  }

  public IContainer<String> loadMultiBackReferenceEntriesIds(
    final String entityId,
    final String multiBackReferenceColumnId) {
    final var query = //
    MULTI_BACK_REFERENCE_QUERY_CREATOR.createQueryToLoadMultiBackReferenceEntriesIds(
      entityId,
      multiBackReferenceColumnId);

    return sqlConnection.getRecordsFromQuery(query).to(r -> r.getStoredAtOneBasedIndex(1));
  }

  public IContainer<MultiReferenceEntryDto> loadMultiReferenceEntries(
    final String entityId,
    final String multiReferenceColumnId,
    final DatabaseViewDto databaseView) {
    final var query = //
    MULTI_REFERENCE_QUERY_CREATOR.createQueryToLoadMultiReferenceEntries(entityId, multiReferenceColumnId);

    final var sqlRecords = sqlConnection.getRecordsFromQuery(query);

    return //
    sqlRecords.to(
      r -> MULTI_REFERENCE_ENTRY_DTO_MAPPER.mapMultiReferenceEntrySqlRecordToMultiReferenceEntryDto(r, databaseView));
  }

  public IContainer<Object> loadMultiValueEntries(
    final String entityId,
    final ColumnViewDto multiValueColumnView) {
    return sqlConnection
      .getRecordsFromQuery(
        MULTI_VALUE_QUERY_CREATOR.createQueryToLoadMultiValueEntries(
          entityId,
          multiValueColumnView.id()))
      .to(r -> VALUE_MAPPER.mapStringToValue(r.getStoredAtOneBasedIndex(1), multiValueColumnView.dataType()));
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
    final ColumnViewDto columnView,
    final String value) {
    final var fieldType = columnView.fieldType();

    return //
    switch (fieldType) {
      case VALUE_FIELD, OPTIONAL_VALUE_FIELD, REFERENCE, OPTIONAL_REFERENCE, BACK_REFERENCE, OPTIONAL_BACK_REFERENCE ->
        tableContainsEntityWithGivenValueAtGivenSingleColumn(
          tableName,
          columnView.name(),
          value);
      case MULTI_VALUE_FIELD ->
        multiValueEntryExistsForGivenColumnAndValue(columnView.id(), value);
      case MULTI_REFERENCE ->
        multiReferenceEntryExistsForGivenColumnAndReferencedEntity(columnView.id(), value);
      default ->
        throw InvalidArgumentException.forArgument(fieldType);
    };
  }

  public boolean tableContainsEntityWithGivenValueAtGivenColumnIgnoringGivenEntities(
    final String tableName,
    final ColumnViewDto columnView,
    final String value,
    final IContainer<String> entitiesToIgnoreIds) {
    final var fieldType = columnView.fieldType();

    return //
    switch (fieldType) {
      case VALUE_FIELD, OPTIONAL_VALUE_FIELD, REFERENCE, OPTIONAL_REFERENCE, BACK_REFERENCE, OPTIONAL_BACK_REFERENCE ->
        tableContainsEntityWithGivenValueAtGivenSingleColumnIgnoringGivenEntities(
          tableName,
          columnView.name(),
          value,
          entitiesToIgnoreIds);
      case MULTI_VALUE_FIELD ->
        multiValueEntryExistsForGivenColumnAndValueIgnoringGivenEntities(
          columnView.id(),
          value,
          entitiesToIgnoreIds);
      case MULTI_REFERENCE ->
        multiReferenceEntryExistsForGivenColumnAndReferencedEntityIgnoringGivenEntities(
          columnView.id(),
          value,
          entitiesToIgnoreIds);
      default ->
        throw InvalidArgumentException.forArgument(fieldType);
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
