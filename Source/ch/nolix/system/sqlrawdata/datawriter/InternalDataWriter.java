//package declaration
package ch.nolix.system.sqlrawdata.datawriter;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.sql.SqlCollector;
import ch.nolix.core.sql.connection.SqlConnection;
import ch.nolix.coreapi.sqlapi.connectionapi.ISqlConnection;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IEntityHeadDto;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IEntityUpdateDto;
import ch.nolix.systemapi.rawdataapi.datadtoapi.INewEntityDto;
import ch.nolix.systemapi.sqlrawdataapi.sqlsyntaxapi.ISqlSyntaxProvider;
import ch.nolix.systemapi.sqlrawdataapi.statementcreatorapi.IEntityStatementCreator;
import ch.nolix.systemapi.sqlrawdataapi.statementcreatorapi.IMultiReferenceStatementCreator;
import ch.nolix.systemapi.sqlrawdataapi.statementcreatorapi.IMultiValueStatementCreator;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//class
public final class InternalDataWriter {

  //attribute
  private int saveCount;

  //attribute
  private final SqlCollector sqlCollector = new SqlCollector();

  //attribute
  private final ISqlConnection sqlConnection;

  //attribute
  private final IEntityStatementCreator entityStatementCreator;

  //attribute
  private final IMultiValueStatementCreator multiValueStatementCreator;

  //attribute
  private final IMultiReferenceStatementCreator multiReferenceStatementCreator;

  //constructor
  public InternalDataWriter(
    final String databaseName,
    final ISqlConnection sqlConnection,
    final ISqlSyntaxProvider sqlSyntaxProvider) {

    GlobalValidator.assertThat(sqlConnection).thatIsNamed(SqlConnection.class).isNotNull();

    this.sqlConnection = sqlConnection;
    entityStatementCreator = sqlSyntaxProvider.getEntityStatementCreator();
    multiValueStatementCreator = sqlSyntaxProvider.getMultiValueStatemeentCreator();
    multiReferenceStatementCreator = sqlSyntaxProvider.getMultiReferenceStatemeentCreator();

    sqlConnection.executeStatement("USE " + databaseName);
  }

  //method
  public void deleteEntity(final String tableName, final IEntityHeadDto entity) {
    sqlCollector.addSqlStatement(entityStatementCreator.createStatementToDeleteEntityHead(entity.getId()));
    sqlCollector.addSqlStatement(entityStatementCreator.createStatementToDeleteEntity(tableName, entity));
  }

  //method
  public void deleteEntriesFromMultiReference(
    final String entityId,
    final String multiReferenceColumnId) {
    sqlCollector.addSqlStatement(
      multiReferenceStatementCreator.createStatementToDeleteMultiReferenceEntries(entityId, multiReferenceColumnId));
  }

  //method
  public void deleteEntriesFromMultiValue(
    final String entityId,
    final String multiValueColumnId) {
    sqlCollector.addSqlStatement(
      multiValueStatementCreator.createStatementToDeleteMultiValueEntries(entityId, multiValueColumnId));
  }

  //method
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

  //method
  public void deleteEntryFromMultiValue(
    final String entityId,
    final String multiValueColumnId,
    final String entry) {
    sqlCollector.addSqlStatement(
      multiValueStatementCreator.createStatementToDeleteMultiValueEntry(entityId, multiValueColumnId, entry));
  }

  //method
  public void expectGivenSchemaTimestamp(final ITime schemaTimestamp) {
    sqlCollector.addSqlStatement(entityStatementCreator.createStatementToExpectGivenSchemaTimestamp(schemaTimestamp));
  }

  //method
  public void expectTableContainsEntity(final String tableName, final String entityId) {
    sqlCollector
      .addSqlStatement(entityStatementCreator.createStatementToExpectTableContainsEntity(tableName, entityId));
  }

  //method
  public int getSaveCount() {
    return saveCount;
  }

  //method
  public boolean hasChanges() {
    return sqlCollector.containsAny();
  }

  //method
  public void insertEntity(final String tableName, final INewEntityDto newEntity) {

    sqlCollector.addSqlStatement(
      entityStatementCreator.createStatementToInsertEntityHead(tableName, newEntity.getId()));

    sqlCollector.addSqlStatement(entityStatementCreator.createStatementToInsertEntity(tableName, newEntity));
  }

  //method
  public void insertEntryIntoMultiBackReference(
    final String entityId,
    final String multiBackReferenceColumnId,
    final String backReferencedEntityId) {
    //TODO: Implement.
  }

  //method
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

  //method
  public void insertEntryIntoMultiValue(
    final String entityId,
    final String multiValueColumnId,
    final String entry) {
    sqlCollector.addSqlStatement(
      multiValueStatementCreator.createQueryToInsertEntryIntoMultiValue(entityId, multiValueColumnId, entry));
  }

  //method
  public void reset() {
    sqlCollector.clear();
  }

  //method
  public void saveChangesAndReset() {
    try {
      sqlCollector.executeUsingConnection(sqlConnection);
      saveCount++;
    } finally {
      reset();
    }
  }

  //method
  public void setEntityAsUpdated(final String tableName, final IEntityHeadDto entity) {
    sqlCollector.addSqlStatement(entityStatementCreator.createStatementToSetEntityAsUpdated(tableName, entity));
  }

  //method
  public void updateEntityOnTable(final String tableName, final IEntityUpdateDto entityUpdate) {
    sqlCollector.addSqlStatement(entityStatementCreator.createStatementToUpdateEntityOnTable(tableName, entityUpdate));
  }
}
