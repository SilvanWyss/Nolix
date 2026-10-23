/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.sqlmiddata.datawriter;

import ch.nolix.base.sql.connection.AbstractSqlConnection;
import ch.nolix.base.sql.sqltool.SqlCollector;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.sql.connection.ISqlConnection;
import ch.nolix.system.sqlmiddata.statementcreator.EntityStatementCreator;
import ch.nolix.system.sqlmiddata.statementcreator.MultiBackReferenceStatementCreator;
import ch.nolix.system.sqlmiddata.statementcreator.MultiReferenceStatementCreator;
import ch.nolix.system.sqlmiddata.statementcreator.MultiValueStatementCreator;
import ch.nolix.systemapi.middata.model.EntityCreationDto;
import ch.nolix.systemapi.middata.model.EntityDeletionDto;
import ch.nolix.systemapi.middata.model.EntityUpdateDto;
import ch.nolix.systemapi.time.main.ITime;

/**
 * @author Silvan Wyss
 */
public final class ExecutiveDataWriter {
  private static final EntityStatementCreator ENTITY_STATEMENT_CREATOR = new EntityStatementCreator();

  private static final MultiValueStatementCreator MULTI_VALUE_STATEMENT_CREATOR = new MultiValueStatementCreator();

  private static final MultiReferenceStatementCreator MULTI_REFERENCE_STATEMENT_CREATOR = //
  new MultiReferenceStatementCreator();

  private static final MultiBackReferenceStatementCreator MULTI_BACK_REFERENCE_STATEMENT_CREATOR = //
  new MultiBackReferenceStatementCreator();

  private int saveCount;

  private final SqlCollector sqlCollector = new SqlCollector();

  private final ISqlConnection sqlConnection;

  private ExecutiveDataWriter(final String databaseName, final ISqlConnection sqlConnection) {
    Validator.assertThat(sqlConnection).thatIsNamed(AbstractSqlConnection.class).isNotNull();

    this.sqlConnection = sqlConnection;

    sqlConnection.executeStatement("USE " + databaseName);
  }

  /**
   * @param databaseName
   * @param sqlConnection
   * @return a new {@link ExecutiveDataWriter} for the database with the given
   *         databaseName using the given sqlConnection
   */
  public static ExecutiveDataWriter withDatabaseNameAndSqlConnection(
    final String databaseName,
    final ISqlConnection sqlConnection) {
    return new ExecutiveDataWriter(databaseName, sqlConnection);
  }

  /**
   * Adds a change to delete the given entity from the table with the given
   * tableName.
   * 
   * @param tableName
   * @param entity
   */
  public void deleteEntity(final String tableName, final EntityDeletionDto entity) {
    final var deleteEntityIndexStatement = ENTITY_STATEMENT_CREATOR.createStatementToDeleteEntityIndex(entity.id());
    final var deleteEntityStatement = ENTITY_STATEMENT_CREATOR.createStatementToDeleteEntity(tableName, entity);

    sqlCollector.addSqlStatements(deleteEntityIndexStatement, deleteEntityStatement);
  }

  /**
   * Adds a change to delete the entries of the multi reference column with the
   * given multiReferenceColumnId of the entity with the given entityId.
   * 
   * @param entityId
   * @param multiReferenceColumnId
   */
  public void deleteEntriesFromMultiReference(
    final String entityId,
    final String multiReferenceColumnId) {
    final var statement = //
    MULTI_REFERENCE_STATEMENT_CREATOR.createStatementToDeleteMultiReferenceEntries(entityId, multiReferenceColumnId);

    sqlCollector.addSqlStatement(statement);
  }

  /**
   * Adds a change to delete the entries of the multi value column with the given
   * multiValueColumnId of the entity with the given entityId.
   * 
   * @param entityId
   * @param multiValueColumnId
   */
  public void deleteEntriesFromMultiValue(
    final String entityId,
    final String multiValueColumnId) {
    final var statement = //
    MULTI_VALUE_STATEMENT_CREATOR.createStatementToDeleteMultiValueEntries(entityId, multiValueColumnId);

    sqlCollector.addSqlStatement(statement);
  }

  /**
   * Adds a change to delete the entry, that references the entity with the given
   * referencedEntityId, from the multi reference column with the given
   * multiReferenceColumnId of the entity with the given entityId.
   * 
   * @param entityId
   * @param multiReferenceColumnId
   * @param referencedEntityId
   */
  public void deleteEntryFromMultiReference(
    final String entityId,
    final String multiReferenceColumnId,
    final String referencedEntityId) {
    final var statement = //
    MULTI_REFERENCE_STATEMENT_CREATOR.createStatementToDeleteMultiReferenceEntry(
      entityId,
      multiReferenceColumnId,
      referencedEntityId);

    sqlCollector.addSqlStatement(statement);
  }

  /**
   * Adds a change to delete the entry, that references back the entity with the
   * given backReferencedEntityId, from the multi back reference column with the
   * given multiBackReferenceColumnId of the entity with the given entityId.
   * 
   * @param entityId
   * @param multiBackReferenceColumnId
   * @param backReferencedEntityId
   */
  public void deleteMultiBackReferenceEntry(
    final String entityId,
    final String multiBackReferenceColumnId,
    final String backReferencedEntityId) {
    final var statement = //
    MULTI_BACK_REFERENCE_STATEMENT_CREATOR.createStatementToDeleteMultiBackReferenceEntry(
      entityId,
      multiBackReferenceColumnId,
      backReferencedEntityId);

    sqlCollector.addSqlStatement(statement);
  }

  /**
   * Adds a change to delete the given value from the multi value column with the
   * given multiValueColumnId of the entity with the given entityId.
   * 
   * @param entityId
   * @param multiValueColumnId
   * @param value
   */
  public void deleteMultiValueEntry(final String entityId, final String multiValueColumnId, final String value) {
    final var statement = //
    MULTI_VALUE_STATEMENT_CREATOR.createStatementToDeleteMultiValueEntry(entityId, multiValueColumnId, value);

    sqlCollector.addSqlStatement(statement);
  }

  /**
   * Adds an expectation that the database has the given schemaTimestamp.
   * 
   * @param schemaTimestamp
   */
  public void expectGivenSchemaTimestamp(final ITime schemaTimestamp) {
    sqlCollector.addSqlStatement(ENTITY_STATEMENT_CREATOR.createStatementToExpectGivenSchemaTimestamp(schemaTimestamp));
  }

  /**
   * Adds an expectation that the table with the given tableName contains the
   * entity with the given entityId.
   * 
   * @param tableName
   * @param entityId
   */
  public void expectTableContainsEntity(final String tableName, final String entityId) {
    final var statement = ENTITY_STATEMENT_CREATOR.createStatementToExpectTableContainsEntity(tableName, entityId);

    sqlCollector.addSqlStatement(statement);
  }

  /**
   * @return the number of saves of the current {@link ExecutiveDataWriter}
   */
  public int getSaveCount() {
    return saveCount;
  }

  /**
   * @return true if the current {@link ExecutiveDataWriter} has changes, false
   *         otherwise
   */
  public boolean hasChanges() {
    return sqlCollector.containsAny();
  }

  /**
   * Adds a change to insert the given newEntity into the table with the given
   * tableName.
   * 
   * @param tableName
   * @param newEntity
   */
  public void insertEntity(final String tableName, final EntityCreationDto newEntity) {
    final var insertEntityIndexStatement = //
    ENTITY_STATEMENT_CREATOR.createStatementToInsertEntityIndex(tableName, newEntity.id());

    final var insertEntityStatement = ENTITY_STATEMENT_CREATOR.createStatementToInsertEntity(tableName, newEntity);

    sqlCollector.addSqlStatements(insertEntityIndexStatement, insertEntityStatement);
  }

  /**
   * Adds a change to insert an entry, that references back the entity with the
   * given backReferencedEntityId of the table with the given
   * backReferencedEntityTableId, into the multi back reference column with the
   * given multiBackReferenceColumnId of the entity with the given entityId.
   * 
   * @param entityId
   * @param multiBackReferenceColumnId
   * @param backReferencedEntityId
   * @param backReferencedEntityTableId
   */
  public void insertEntryIntoMultiBackReference(
    final String entityId,
    final String multiBackReferenceColumnId,
    final String backReferencedEntityId,
    final String backReferencedEntityTableId) {
    final var statement = //
    MULTI_BACK_REFERENCE_STATEMENT_CREATOR.createStatementToInsertMultiBackReferenceEntry(
      entityId,
      multiBackReferenceColumnId,
      backReferencedEntityId,
      backReferencedEntityTableId);

    sqlCollector.addSqlStatement(statement);
  }

  /**
   * Adds a change to insert the given entry into the multi value column with the
   * given multiValueColumnId of the entity with the given entityId.
   * 
   * @param entityId
   * @param multiValueColumnId
   * @param entry
   */
  public void insertEntryIntoMultiValue(
    final String entityId,
    final String multiValueColumnId,
    final String entry) {
    final var statement = MULTI_VALUE_STATEMENT_CREATOR.createStatementToInsertMultiValueEntry(entityId,
      multiValueColumnId, entry);

    sqlCollector.addSqlStatement(statement);
  }

  /**
   * Adds a change to insert an entry, that references the entity with the given
   * referencedEntityId of the table with the given referencedEntityTableId, into
   * the multi reference column with the given multiReferenceColumnId of the
   * entity with the given entityId.
   * 
   * @param entityId
   * @param multiReferenceColumnId
   * @param referencedEntityId
   * @param referencedEntityTableId
   */
  public void insertMultiReferenceEntry(
    final String entityId,
    final String multiReferenceColumnId,
    final String referencedEntityId,
    final String referencedEntityTableId) {
    final var statement = //
    MULTI_REFERENCE_STATEMENT_CREATOR.createStatementToInsertMultiReferenceEntry(
      entityId,
      multiReferenceColumnId,
      referencedEntityId,
      referencedEntityTableId);

    sqlCollector.addSqlStatement(statement);
  }

  /**
   * Removes all changes and expectations of the current
   * {@link ExecutiveDataWriter}.
   */
  public void reset() {
    sqlCollector.clear();
  }

  /**
   * Saves the changes of the current {@link ExecutiveDataWriter} and resets the
   * current {@link ExecutiveDataWriter}.
   */
  public void saveChangesAndReset() {
    sqlCollector.executeAndClearUsingConnection(sqlConnection);

    saveCount++;
  }

  /**
   * Adds a change to apply the given entityUpdate on the table with the given
   * tableName.
   * 
   * @param tableName
   * @param entityUpdate
   */
  public void updateEntityOnTable(final String tableName, final EntityUpdateDto entityUpdate) {
    final var statement = ENTITY_STATEMENT_CREATOR.createStatementToUpdateEntityOnTable(tableName, entityUpdate);

    sqlCollector.addSqlStatement(statement);
  }
}
