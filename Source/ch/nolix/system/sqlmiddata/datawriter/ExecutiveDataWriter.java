package ch.nolix.system.sqlmiddata.datawriter;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.sql.connection.AbstractSqlConnection;
import ch.nolix.core.sql.sqltool.SqlCollector;
import ch.nolix.coreapi.sql.connection.ISqlConnection;
import ch.nolix.system.sqlmiddata.statementcreator.EntityStatementCreator;
import ch.nolix.system.sqlmiddata.statementcreator.MultiBackReferenceStatementCreator;
import ch.nolix.system.sqlmiddata.statementcreator.MultiReferenceStatementCreator;
import ch.nolix.system.sqlmiddata.statementcreator.MultiValueStatementCreator;
import ch.nolix.systemapi.middataapi.modelapi.EntityCreationDto;
import ch.nolix.systemapi.middataapi.modelapi.EntityDeletionDto;
import ch.nolix.systemapi.middataapi.modelapi.EntityUpdateDto;
import ch.nolix.systemapi.sqlmiddataapi.statementcreatorapi.IEntityStatementCreator;
import ch.nolix.systemapi.sqlmiddataapi.statementcreatorapi.IMultiBackReferenceStatementCreator;
import ch.nolix.systemapi.sqlmiddataapi.statementcreatorapi.IMultiReferenceStatementCreator;
import ch.nolix.systemapi.sqlmiddataapi.statementcreatorapi.IMultiValueStatementCreator;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public final class ExecutiveDataWriter {

  private static final IEntityStatementCreator ENTITY_STATEMENT_CREATOR = new EntityStatementCreator();

  private static final IMultiValueStatementCreator MULTI_VALUE_STATEMENT_CREATOR = new MultiValueStatementCreator();

  private static final IMultiReferenceStatementCreator MULTI_REFERENCE_STATEMENT_CREATOR = //
  new MultiReferenceStatementCreator();

  private static final IMultiBackReferenceStatementCreator MULTI_BACK_REFERENCE_STATEMENT_CREATOR = //
  new MultiBackReferenceStatementCreator();

  private int saveCount;

  private final SqlCollector sqlCollector = new SqlCollector();

  private final ISqlConnection sqlConnection;

  public ExecutiveDataWriter(
    final String databaseName,
    final ISqlConnection sqlConnection) {

    Validator.assertThat(sqlConnection).thatIsNamed(AbstractSqlConnection.class).isNotNull();

    this.sqlConnection = sqlConnection;

    sqlConnection.executeStatement("USE " + databaseName);
  }

  public void deleteEntity(final String tableName, final EntityDeletionDto entity) {

    final var deleteEntityIndexStatement = ENTITY_STATEMENT_CREATOR.createStatementToDeleteEntityIndex(entity.id());
    final var deleteEntityStatement = ENTITY_STATEMENT_CREATOR.createStatementToDeleteEntity(tableName, entity);

    sqlCollector.addSqlStatement(deleteEntityIndexStatement, deleteEntityStatement);
  }

  public void deleteEntriesFromMultiReference(
    final String entityId,
    final String multiReferenceColumnId) {

    final var statement = //
    MULTI_REFERENCE_STATEMENT_CREATOR.createStatementToDeleteMultiReferenceEntries(entityId, multiReferenceColumnId);

    sqlCollector.addSqlStatement(statement);
  }

  public void deleteEntriesFromMultiValue(
    final String entityId,
    final String multiValueColumnId) {

    final var statement = //
    MULTI_VALUE_STATEMENT_CREATOR.createStatementToDeleteMultiValueEntries(entityId, multiValueColumnId);

    sqlCollector.addSqlStatement(statement);
  }

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

  public void deleteMultiValueEntry(final String entityId, final String multiValueColumnId, final String value) {

    final var statement = //
    MULTI_VALUE_STATEMENT_CREATOR.createStatementToDeleteMultiValueEntry(entityId, multiValueColumnId, value);

    sqlCollector.addSqlStatement(statement);
  }

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

  public void expectGivenSchemaTimestamp(final ITime schemaTimestamp) {
    sqlCollector.addSqlStatement(ENTITY_STATEMENT_CREATOR.createStatementToExpectGivenSchemaTimestamp(schemaTimestamp));
  }

  public void expectTableContainsEntity(final String tableName, final String entityId) {

    final var statement = ENTITY_STATEMENT_CREATOR.createStatementToExpectTableContainsEntity(tableName, entityId);

    sqlCollector.addSqlStatement(statement);
  }

  public int getSaveCount() {
    return saveCount;
  }

  public boolean hasChanges() {
    return sqlCollector.containsAny();
  }

  public void insertEntity(final String tableName, final EntityCreationDto newEntity) {

    final var insertEntityIndexStatement = //
    ENTITY_STATEMENT_CREATOR.createStatementToInsertEntityIndex(tableName, newEntity.id());

    final var insertEntityStatement = ENTITY_STATEMENT_CREATOR.createStatementToInsertEntity(tableName, newEntity);

    sqlCollector.addSqlStatement(insertEntityIndexStatement, insertEntityStatement);
  }

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

  public void insertEntryIntoMultiValue(
    final String entityId,
    final String multiValueColumnId,
    final String entry) {

    final var statement = MULTI_VALUE_STATEMENT_CREATOR.createStatementToInsertMultiValueEntry(entityId,
      multiValueColumnId, entry);

    sqlCollector.addSqlStatement(statement);
  }

  public void reset() {
    sqlCollector.clear();
  }

  public void saveChangesAndReset() {

    sqlCollector.executeAndClearUsingConnection(sqlConnection);

    saveCount++;
  }

  public void updateEntityOnTable(final String tableName, final EntityUpdateDto entityUpdate) {

    final var statement = ENTITY_STATEMENT_CREATOR.createStatementToUpdateEntityOnTable(tableName, entityUpdate);

    sqlCollector.addSqlStatement(statement);
  }
}
