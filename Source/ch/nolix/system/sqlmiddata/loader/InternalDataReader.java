/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.sqlmiddata.loader;

import ch.nolix.base.sql.connection.AbstractSqlConnection;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.baseapi.sql.connection.ISqlConnection;
import ch.nolix.system.middata.valuemapper.ValueMapper;
import ch.nolix.system.sqlmiddata.modelmapper.MultiBackReferenceEntryDtoMapper;
import ch.nolix.system.sqlmiddata.modelmapper.MultiReferenceEntryDtoMapper;
import ch.nolix.system.sqlmiddata.querycreator.EntityQueryCreator;
import ch.nolix.system.sqlmiddata.querycreator.MultiBackReferenceQueryCreator;
import ch.nolix.system.sqlmiddata.querycreator.MultiReferenceQueryCreator;
import ch.nolix.system.sqlmiddata.querycreator.MultiValueQueryCreator;
import ch.nolix.system.time.main.Time;
import ch.nolix.systemapi.middata.model.MultiBackReferenceEntryDto;
import ch.nolix.systemapi.middata.model.MultiReferenceEntryDto;
import ch.nolix.systemapi.midschema.structure.TableIdentification;
import ch.nolix.systemapi.midschemainfo.model.ColumnInfoDto;
import ch.nolix.systemapi.midschemainfo.model.DatabaseInfoDto;

/**
 * @author Silvan Wyss
 */
public final class InternalDataReader {
  private static final EntityQueryCreator ENTITY_QUERY_CREATOR = new EntityQueryCreator();

  private static final MultiValueQueryCreator MULTI_VALUE_QUERY_CREATOR = new MultiValueQueryCreator();

  private static final MultiReferenceQueryCreator MULTI_REFERENCE_QUERY_CREATOR = new MultiReferenceQueryCreator();

  private static final MultiBackReferenceQueryCreator MULTI_BACK_REFERENCE_QUERY_CREATOR = //
  new MultiBackReferenceQueryCreator();

  private static final MultiReferenceEntryDtoMapper MULTI_REFERENCE_ENTRY_DTO_MAPPER = //
  new MultiReferenceEntryDtoMapper();

  private static final MultiBackReferenceEntryDtoMapper MULTI_BACK_REFERENCE_ENTRY_DTO_MAPPER = //
  new MultiBackReferenceEntryDtoMapper();

  private static final ValueMapper VALUE_MAPPER = new ValueMapper();

  private final String databaseName;

  private final ISqlConnection sqlConnection;

  private InternalDataReader(final String databaseName, final ISqlConnection sqlConnection) {
    Validator.assertThat(databaseName).thatIsNamed("database name").isNotBlank();
    Validator.assertThat(sqlConnection).thatIsNamed(AbstractSqlConnection.class).isNotNull();

    this.databaseName = databaseName;
    this.sqlConnection = sqlConnection;

    sqlConnection.executeStatement("USE " + databaseName);
  }

  /**
   * @param databaseName
   * @param sqlConnection
   * @return a new {@link InternalDataReader} for the database with the given
   *         databaseName using the given sqlConnection
   */
  public static InternalDataReader withDatabaseNameAndSqlConnection(
    final String databaseName,
    final ISqlConnection sqlConnection) {
    return new InternalDataReader(databaseName, sqlConnection);
  }

  /**
   * @return the name of the database of the current {@link InternalDataReader}
   */
  public String getDatabaseName() {
    return databaseName;
  }

  /**
   * @param tableName
   * @return the number of entities of the table with the given tableName
   */
  public int getEntityCount(String tableName) {
    final var query = ENTITY_QUERY_CREATOR.createQueryToCountEntities(tableName);
    final var sqlRecord = sqlConnection.getSingleRecordFromQuery(query);

    return Integer.valueOf(sqlRecord.getStoredSingle());
  }

  /**
   * @return the schema timestamp of the database of the current
   *         {@link InternalDataReader}
   */
  public Time getSchemaTimestamp() {
    return Time.fromString(
      sqlConnection
        .getSingleRecordFromQuery(ENTITY_QUERY_CREATOR.createQueryToLoadSchemaTimestamp())
        .getStoredAtOneBasedIndex(1));
  }

  /**
   * @param table
   * @param entityId
   * @param multiBackReferenceColumnId
   * @return the entries of the multi back reference column with the given
   *         multiBackReferenceColumnId of the entity with the given entityId of
   *         the given table
   */
  public ExtendedIterable<MultiBackReferenceEntryDto> loadMultiBackReferenceEntries(
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

  /**
   * @param entityId
   * @param multiBackReferenceColumnId
   * @return the ids of the entries of the multi back reference column with the
   *         given multiBackReferenceColumnId of the entity with the given
   *         entityId
   */
  public ExtendedIterable<String> loadMultiBackReferenceEntriesIds(
    final String entityId,
    final String multiBackReferenceColumnId) {
    final var query = //
    MULTI_BACK_REFERENCE_QUERY_CREATOR.createQueryToLoadMultiBackReferenceEntriesIds(
      entityId,
      multiBackReferenceColumnId);

    return sqlConnection.getRecordsFromQuery(query).to(r -> r.getStoredAtOneBasedIndex(1));
  }

  /**
   * @param entityId
   * @param multiReferenceColumnId
   * @param databaseView
   * @return the entries of the multi reference column with the given
   *         multiReferenceColumnId of the entity with the given entityId
   */
  public ExtendedIterable<MultiReferenceEntryDto> loadMultiReferenceEntries(
    final String entityId,
    final String multiReferenceColumnId,
    final DatabaseInfoDto databaseView) {
    final var query = //
    MULTI_REFERENCE_QUERY_CREATOR.createQueryToLoadMultiReferenceEntries(entityId, multiReferenceColumnId);

    final var sqlRecords = sqlConnection.getRecordsFromQuery(query);

    return //
    sqlRecords.to(
      r -> MULTI_REFERENCE_ENTRY_DTO_MAPPER.mapMultiReferenceEntrySqlRecordToMultiReferenceEntryDto(r, databaseView));
  }

  /**
   * @param entityId
   * @param multiValueColumnView
   * @return the entries of the multi value column of the given
   *         multiValueColumnView of the entity with the given entityId
   */
  public ExtendedIterable<Object> loadMultiValueEntries(
    final String entityId,
    final ColumnInfoDto multiValueColumnView) {
    return sqlConnection
      .getRecordsFromQuery(
        MULTI_VALUE_QUERY_CREATOR.createQueryToLoadMultiValueEntries(
          entityId,
          multiValueColumnView.id()))
      .to(r -> VALUE_MAPPER.mapStringToValue(r.getStoredAtOneBasedIndex(1), multiValueColumnView.dataType()));
  }

  /**
   * @param tableName
   * @param id
   * @return true if the table with the given tableName contains an entity with
   *         the given id, false otherwise
   */
  public boolean tableContainsEntityWithGivenId(final String tableName, final String id) {
    final var entityCount = Integer.valueOf(
      sqlConnection
        .getSingleRecordFromQuery(ENTITY_QUERY_CREATOR.createQueryToCountEntitiesWithGivenId(tableName, id))
        .getStoredAtOneBasedIndex(1));

    return entityCount > 0;
  }

  /**
   * @param tableName
   * @param columnView
   * @param value
   * @return true if the table with the given tableName contains an entity with
   *         the given value at the column of the given columnView, false
   *         otherwise
   */
  public boolean tableContainsEntityWithGivenValueAtGivenColumn(
    final String tableName,
    final ColumnInfoDto columnView,
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

  /**
   * @param tableName
   * @param columnView
   * @param value
   * @param entitiesToIgnoreIds
   * @return true if the table with the given tableName contains an entity, that
   *         does not have one of the given entitiesToIgnoreIds, with the given
   *         value at the column of the given columnView, false otherwise
   */
  public boolean tableContainsEntityWithGivenValueAtGivenColumnIgnoringGivenEntities(
    final String tableName,
    final ColumnInfoDto columnView,
    final String value,
    final ExtendedIterable<String> entitiesToIgnoreIds) {
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
    final ExtendedIterable<String> entitiesToIgnoreIds) {
    final var query = //
    MULTI_REFERENCE_QUERY_CREATOR
      .createQueryToCountMultiReferenceEntriesForGivenColumnAndReferencedEntityIgnoringGivenEntities(
        columnId,
        referencedEntityId,
        entitiesToIgnoreIds);

    final var localRecord = sqlConnection.getSingleRecordFromQuery(query);
    final var entityCount = Integer.valueOf(localRecord.getStoredFirstNonNull());

    return entityCount > 0;
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
    final ExtendedIterable<String> entitiesToIgnoreIds) {
    final var query = //
    MULTI_VALUE_QUERY_CREATOR.createQueryToCountMultiValueEntriesForGivenColumnAndValueIgnoringGivenEntities(
      columnId,
      value,
      entitiesToIgnoreIds);

    final var localRecord = sqlConnection.getSingleRecordFromQuery(query);
    final var entityCount = Integer.valueOf(localRecord.getStoredFirstNonNull());

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
    final ExtendedIterable<String> entitiesToIgnoreIds) {
    final var query = //
    ENTITY_QUERY_CREATOR.createQueryToCountEntitiesWithGivenValueAtGivenColumnIgnoringGivenEntities(
      tableName,
      columnName,
      value,
      entitiesToIgnoreIds);

    final var localRecord = sqlConnection.getSingleRecordFromQuery(query);
    final var entityCount = Integer.valueOf(localRecord.getStoredFirstNonNull());

    return entityCount > 0;
  }
}
