package ch.nolix.system.sqlrawdata.datawriter;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.sql.connection.SqlConnection;
import ch.nolix.core.sql.sqltool.SqlCollector;
import ch.nolix.coreapi.sqlapi.connectionapi.ISqlConnection;
import ch.nolix.system.sqlrawdata.statementcreator.EntityStatementCreator;
import ch.nolix.system.sqlrawdata.statementcreator.MultiBackReferenceStatementCreator;
import ch.nolix.system.sqlrawdata.statementcreator.MultiReferenceStatementCreator;
import ch.nolix.system.sqlrawdata.statementcreator.MultiValueStatementCreator;
import ch.nolix.systemapi.rawdataapi.model.EntityCreationDto;
import ch.nolix.systemapi.rawdataapi.model.EntityDeletionDto;
import ch.nolix.systemapi.rawdataapi.model.EntityUpdateDto;
import ch.nolix.systemapi.sqlrawdataapi.statementcreatorapi.IEntityStatementCreator;
import ch.nolix.systemapi.sqlrawdataapi.statementcreatorapi.IMultiBackReferenceStatementCreator;
import ch.nolix.systemapi.sqlrawdataapi.statementcreatorapi.IMultiReferenceStatementCreator;
import ch.nolix.systemapi.sqlrawdataapi.statementcreatorapi.IMultiValueStatementCreator;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public final class InternalDataWriter {

  private static final IEntityStatementCreator ENTITY_STATEMENT_CREATOR = new EntityStatementCreator();

  private static final IMultiValueStatementCreator MULTI_VALUE_STATEMENT_CREATOR = new MultiValueStatementCreator();

  private static final IMultiReferenceStatementCreator MULTI_REFERENCE_STATEMENT_CREATOR = //
  new MultiReferenceStatementCreator();

  private static final IMultiBackReferenceStatementCreator MULTI_BACK_REFERENCE_STATEMENT_CREATOR = //
  new MultiBackReferenceStatementCreator();

  private int saveCount;

  private final SqlCollector sqlCollector = new SqlCollector();

  private final ISqlConnection sqlConnection;

  public InternalDataWriter(
    final String databaseName,
    final ISqlConnection sqlConnection) {

    GlobalValidator.assertThat(sqlConnection).thatIsNamed(SqlConnection.class).isNotNull();

    this.sqlConnection = sqlConnection;

    sqlConnection.executeStatement("USE " + databaseName);
  }

  public void deleteEntity(final String tableName, final EntityDeletionDto entity) {
    sqlCollector.addSqlStatement(ENTITY_STATEMENT_CREATOR.createStatementToDeleteEntityIndex(entity.id()));
    sqlCollector.addSqlStatement(ENTITY_STATEMENT_CREATOR.createStatementToDeleteEntity(tableName, entity));
  }

  public void deleteEntriesFromMultiReference(
    final String entityId,
    final String multiReferenceColumnId) {
    sqlCollector.addSqlStatement(
      MULTI_REFERENCE_STATEMENT_CREATOR.createStatementToDeleteMultiReferenceEntries(entityId, multiReferenceColumnId));
  }

  public void deleteEntriesFromMultiValue(
    final String entityId,
    final String multiValueColumnId) {
    sqlCollector.addSqlStatement(
      MULTI_VALUE_STATEMENT_CREATOR.createStatementToDeleteMultiValueEntries(entityId, multiValueColumnId));
  }

  public void deleteEntryFromMultiReference(
    final String entityId,
    final String multiReferenceColumnId,
    final String referencedEntityId) {
    sqlCollector.addSqlStatement(
      MULTI_REFERENCE_STATEMENT_CREATOR.createStatementToDeleteMultiReferenceEntry(
        entityId,
        multiReferenceColumnId,
        referencedEntityId));
  }

  public void deleteEntryFromMultiValue(
    final String entityId,
    final String multiValueColumnId,
    final String entry) {
    sqlCollector.addSqlStatement(
      MULTI_VALUE_STATEMENT_CREATOR.createStatementToDeleteMultiValueEntry(entityId, multiValueColumnId, entry));
  }

  public void deleteMultiBackReferenceEntry(
    final String entityId,
    final String multiBackReferenceColumnId,
    final String backReferencedEntityId) {

    final var statement = MULTI_BACK_REFERENCE_STATEMENT_CREATOR.createStatementToDeleteMultiBackReferenceEntry(
      entityId,
      multiBackReferenceColumnId,
      backReferencedEntityId);

    sqlCollector.addSqlStatement(statement);
  }

  public void expectGivenSchemaTimestamp(final ITime schemaTimestamp) {
    sqlCollector.addSqlStatement(ENTITY_STATEMENT_CREATOR.createStatementToExpectGivenSchemaTimestamp(schemaTimestamp));
  }

  public void expectTableContainsEntity(final String tableName, final String entityId) {
    sqlCollector
      .addSqlStatement(ENTITY_STATEMENT_CREATOR.createStatementToExpectTableContainsEntity(tableName, entityId));
  }

  public int getSaveCount() {
    return saveCount;
  }

  public boolean hasChanges() {
    return sqlCollector.containsAny();
  }

  public void insertEntity(final String tableName, final EntityCreationDto newEntity) {

    sqlCollector.addSqlStatement(ENTITY_STATEMENT_CREATOR.createStatementToInsertEntityIndex(tableName, newEntity.id()));

    sqlCollector.addSqlStatement(ENTITY_STATEMENT_CREATOR.createStatementToInsertEntity(tableName, newEntity));
  }

  public void insertEntryIntoMultiBackReference(
    final String entityId,
    final String multiBackReferenceColumnId,
    final String backReferencedEntityId) {

    final var statement = MULTI_BACK_REFERENCE_STATEMENT_CREATOR.createStatementToInsertMultiBackReferenceEntry(
      entityId,
      multiBackReferenceColumnId,
      backReferencedEntityId);

    sqlCollector.addSqlStatement(statement);
  }

  public void insertEntryIntoMultiReference(
    final String entityId,
    final String multiReferenceColumnId,
    final String referencedEntityId) {
    sqlCollector.addSqlStatement(
      MULTI_REFERENCE_STATEMENT_CREATOR.createStatementToInsertMultiReferenceEntry(
        entityId,
        multiReferenceColumnId,
        referencedEntityId));
  }

  public void insertEntryIntoMultiValue(
    final String entityId,
    final String multiValueColumnId,
    final String entry) {
    sqlCollector.addSqlStatement(
      MULTI_VALUE_STATEMENT_CREATOR.createStatementToInsertMultiValueEntry(entityId, multiValueColumnId, entry));
  }

  public void reset() {
    sqlCollector.clear();
  }

  public void saveChangesAndReset() {
    try {
      sqlCollector.executeAndClearUsingConnection(sqlConnection);
      saveCount++;
    } finally {
      reset();
    }
  }

  public void updateEntityOnTable(final String tableName, final EntityUpdateDto entityUpdate) {
    sqlCollector
      .addSqlStatement(ENTITY_STATEMENT_CREATOR.createStatementToUpdateEntityOnTable(tableName, entityUpdate));
  }
}
