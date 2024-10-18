package ch.nolix.system.sqlrawdata.datawriter;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.sql.SqlCollector;
import ch.nolix.core.sql.connection.SqlConnection;
import ch.nolix.coreapi.sqlapi.connectionapi.ISqlConnection;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IEntityHeadDto;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IEntityUpdateDto;
import ch.nolix.systemapi.rawdataapi.datadtoapi.INewEntityDto;
import ch.nolix.systemapi.sqlrawdataapi.sqlsyntaxapi.ISqlSyntaxProvider;
import ch.nolix.systemapi.sqlrawdataapi.statementcreatorapi.IEntityStatementCreator;
import ch.nolix.systemapi.sqlrawdataapi.statementcreatorapi.IMultiBackReferenceStatementCreator;
import ch.nolix.systemapi.sqlrawdataapi.statementcreatorapi.IMultiReferenceStatementCreator;
import ch.nolix.systemapi.sqlrawdataapi.statementcreatorapi.IMultiValueStatementCreator;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public final class InternalDataWriter {

  private int saveCount;

  private final SqlCollector sqlCollector = new SqlCollector();

  private final ISqlConnection sqlConnection;

  private final IEntityStatementCreator entityStatementCreator;

  private final IMultiValueStatementCreator multiValueStatementCreator;

  private final IMultiReferenceStatementCreator multiReferenceStatementCreator;

  private final IMultiBackReferenceStatementCreator multiBackReferenceStatementCreator;

  public InternalDataWriter(
    final String databaseName,
    final ISqlConnection sqlConnection,
    final ISqlSyntaxProvider sqlSyntaxProvider) {

    GlobalValidator.assertThat(sqlConnection).thatIsNamed(SqlConnection.class).isNotNull();

    this.sqlConnection = sqlConnection;
    entityStatementCreator = sqlSyntaxProvider.getEntityStatementCreator();
    multiValueStatementCreator = sqlSyntaxProvider.getMultiValueStatemeentCreator();
    multiReferenceStatementCreator = sqlSyntaxProvider.getMultiReferenceStatemeentCreator();
    multiBackReferenceStatementCreator = sqlSyntaxProvider.getMultiBackReferenceStatemeentCreator();

    sqlConnection.executeStatement("USE " + databaseName);
  }

  public void deleteEntity(final String tableName, final IEntityHeadDto entity) {
    sqlCollector.addSqlStatement(entityStatementCreator.createStatementToDeleteEntityHead(entity.getId()));
    sqlCollector.addSqlStatement(entityStatementCreator.createStatementToDeleteEntity(tableName, entity));
  }

  public void deleteEntriesFromMultiReference(
    final String entityId,
    final String multiReferenceColumnId) {
    sqlCollector.addSqlStatement(
      multiReferenceStatementCreator.createStatementToDeleteMultiReferenceEntries(entityId, multiReferenceColumnId));
  }

  public void deleteEntriesFromMultiValue(
    final String entityId,
    final String multiValueColumnId) {
    sqlCollector.addSqlStatement(
      multiValueStatementCreator.createStatementToDeleteMultiValueEntries(entityId, multiValueColumnId));
  }

  public void deleteEntryFromMultiReference(
    final String entityId,
    final String multiReferenceColumnId,
    final String referencedEntityId) {
    sqlCollector.addSqlStatement(
      multiReferenceStatementCreator.createStatementToDeleteMultiReferenceEntry(
        entityId,
        multiReferenceColumnId,
        referencedEntityId));
  }

  public void deleteEntryFromMultiValue(
    final String entityId,
    final String multiValueColumnId,
    final String entry) {
    sqlCollector.addSqlStatement(
      multiValueStatementCreator.createStatementToDeleteMultiValueEntry(entityId, multiValueColumnId, entry));
  }

  public void deleteMultiBackReferenceEntry(
    final String entityId,
    final String multiBackReferenceColumnId,
    final String backReferencedEntityId) {

    final var statement = multiBackReferenceStatementCreator.createStatementToDeleteMultiBackReferenceEntry(
      entityId,
      multiBackReferenceColumnId,
      backReferencedEntityId);

    sqlCollector.addSqlStatement(statement);
  }

  public void expectGivenSchemaTimestamp(final ITime schemaTimestamp) {
    sqlCollector.addSqlStatement(entityStatementCreator.createStatementToExpectGivenSchemaTimestamp(schemaTimestamp));
  }

  public void expectTableContainsEntity(final String tableName, final String entityId) {
    sqlCollector
      .addSqlStatement(entityStatementCreator.createStatementToExpectTableContainsEntity(tableName, entityId));
  }

  public int getSaveCount() {
    return saveCount;
  }

  public boolean hasChanges() {
    return sqlCollector.containsAny();
  }

  public void insertEntity(final String tableName, final INewEntityDto newEntity) {

    sqlCollector.addSqlStatement(
      entityStatementCreator.createStatementToInsertEntityHead(tableName, newEntity.getId()));

    sqlCollector.addSqlStatement(entityStatementCreator.createStatementToInsertEntity(tableName, newEntity));
  }

  public void insertEntryIntoMultiBackReference(
    final String entityId,
    final String multiBackReferenceColumnId,
    final String backReferencedEntityId) {

    final var statement = multiBackReferenceStatementCreator.createStatementToInsertMultiBackReferenceEntry(
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
      multiReferenceStatementCreator.createStatementToInsertMultiReferenceEntry(
        entityId,
        multiReferenceColumnId,
        referencedEntityId));
  }

  public void insertEntryIntoMultiValue(
    final String entityId,
    final String multiValueColumnId,
    final String entry) {
    sqlCollector.addSqlStatement(
      multiValueStatementCreator.createStatementToInsertMultiValueEntry(entityId, multiValueColumnId, entry));
  }

  public void reset() {
    sqlCollector.clear();
  }

  public void saveChangesAndReset() {
    try {
      sqlCollector.executeUsingConnection(sqlConnection);
      saveCount++;
    } finally {
      reset();
    }
  }

  public void setEntityAsUpdated(final String tableName, final IEntityHeadDto entity) {
    sqlCollector.addSqlStatement(entityStatementCreator.createStatementToSetEntityAsUpdated(tableName, entity));
  }

  public void updateEntityOnTable(final String tableName, final IEntityUpdateDto entityUpdate) {
    sqlCollector.addSqlStatement(entityStatementCreator.createStatementToUpdateEntityOnTable(tableName, entityUpdate));
  }
}
