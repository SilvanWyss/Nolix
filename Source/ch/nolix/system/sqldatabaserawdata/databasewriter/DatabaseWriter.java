//package declaration
package ch.nolix.system.sqldatabaserawdata.databasewriter;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.groupcloseable.CloseController;
import ch.nolix.core.sql.SqlConnection;
import ch.nolix.core.sql.SqlConnectionPool;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.rawdatabaseapi.dataadapterapi.IDataWriter;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityHeadDto;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityUpdateDto;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.INewEntityDto;
import ch.nolix.systemapi.rawdatabaseapi.schemainfoapi.IColumnInfo;
import ch.nolix.systemapi.rawdatabaseapi.schemainfoapi.ITableInfo;
import ch.nolix.systemapi.rawdatabaseapi.sqlsyntaxapi.ISqlSyntaxProvider;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//class
public final class DatabaseWriter implements IDataWriter {

  //attribute
  private final CloseController closeController = CloseController.forElement(this);

  //attribute
  private final InternalDatabaseWriter internalDatabaseWriter;

  //multi-attribute
  private final IContainer<ITableInfo> tableInfos;

  //constructor
  private DatabaseWriter(
    final String databaseName,
    final SqlConnection sqlConnection,
    final IContainer<ITableInfo> tableInfos,
    final ISqlSyntaxProvider sqlSyntaxProvider) {

    GlobalValidator.assertThat(tableInfos).thatIsNamed("table definitions").isNotNull();

    internalDatabaseWriter = new InternalDatabaseWriter(databaseName, sqlConnection, sqlSyntaxProvider);
    this.tableInfos = tableInfos;

    createCloseDependencyTo(sqlConnection);
  }

  //static method
  public static DatabaseWriter forDatabaseWithGivenNameUsingConnectionFromGivenPoolAndTableInfosAndSqlSyntaxProvider(
    final String databaseName,
    final SqlConnectionPool sqlConnectionPool,
    final IContainer<ITableInfo> tableInfos,
    final ISqlSyntaxProvider sqlSyntaxProvider) {
    return new DatabaseWriter(databaseName, sqlConnectionPool.borrowSqlConnection(), tableInfos, sqlSyntaxProvider);
  }

  //method
  @Override
  public void deleteMultiReferenceEntries(
    final String tableName,
    final String entityId,
    final String multiReferenceColumnName) {
    internalDatabaseWriter.deleteEntriesFromMultiReference(
      entityId,
      getColumnDefinitionByTableNameAndColumnName(tableName, multiReferenceColumnName).getColumnId());
  }

  //method
  @Override
  public void deleteMultiValueEntries(
    final String tableName,
    final String entityId,
    final String multiValueColumnName) {
    internalDatabaseWriter.deleteEntriesFromMultiValue(
      entityId,
      getColumnDefinitionByTableNameAndColumnName(tableName, multiValueColumnName).getColumnId());
  }

  //method
  @Override
  public void deleteMultiReferenceEntry(
    final String tableName,
    final String entityId,
    final String multiRefereceColumnName,
    final String referencedEntityId) {
    internalDatabaseWriter.deleteEntryFromMultiReference(
      entityId,
      getColumnDefinitionByTableNameAndColumnName(tableName, multiRefereceColumnName).getColumnId(),
      referencedEntityId);
  }

  //method
  @Override
  public void deleteMultiValueEntry(
    final String tableName,
    final String entityId,
    final String multiValueColumnName,
    final String entry) {
    internalDatabaseWriter.deleteEntryFromMultiValue(
      entityId,
      getColumnDefinitionByTableNameAndColumnName(tableName, multiValueColumnName).getColumnId(),
      entry);
  }

  //method
  @Override
  public void deleteEntity(final String tableName, final IEntityHeadDto entity) {
    internalDatabaseWriter.deleteEntity(tableName, entity);
  }

  //method
  @Override
  public void expectGivenSchemaTimestamp(final ITime schemaTimestamp) {
    internalDatabaseWriter.expectGivenSchemaTimestamp(schemaTimestamp);
  }

  //method
  @Override
  public void expectTableContainsEntity(final String tableName, final String entityId) {
    internalDatabaseWriter.expectTableContainsEntity(tableName, entityId);
  }

  //method
  @Override
  public CloseController getStoredCloseController() {
    return closeController;
  }

  //method
  @Override
  public int getSaveCount() {
    return internalDatabaseWriter.getSaveCount();
  }

  //method
  @Override
  public boolean hasChanges() {
    return internalDatabaseWriter.hasChanges();
  }

  //method
  @Override
  public void insertMultiReferenceEntry(
    final String tableName,
    final String entityId,
    final String multiReferenceColumnName,
    final String referencedEntityId) {
    internalDatabaseWriter.insertEntryIntoMultiReference(
      entityId,
      getColumnDefinitionByTableNameAndColumnName(tableName, multiReferenceColumnName).getColumnId(),
      referencedEntityId);
  }

  //method
  @Override
  public void insertMultiValueEntry(
    final String tableName,
    final String entityId,
    final String multiValueColumnName,
    final String entry) {
    internalDatabaseWriter.insertEntryIntoMultiValue(
      entityId,
      getColumnDefinitionByTableNameAndColumnName(tableName, multiValueColumnName).getColumnId(),
      entry);
  }

  //method
  @Override
  public void insertNewEntity(final String tableName, final INewEntityDto newEntity) {
    internalDatabaseWriter.insertNewEntity(tableName, newEntity);
  }

  //method
  @Override
  public void noteClose() {
    //Does nothing.
  }

  //method
  @Override
  public void reset() {
    internalDatabaseWriter.reset();
  }

  //method
  @Override
  public void saveChanges() {
    internalDatabaseWriter.saveChangesAndReset();
  }

  //method
  @Override
  public void setEntityAsUpdated(final String tableName, final IEntityHeadDto entity) {
    internalDatabaseWriter.setEntityAsUpdated(tableName, entity);
  }

  //method
  @Override
  public void updateEntity(final String tableName, final IEntityUpdateDto entityUpdate) {
    internalDatabaseWriter.updateEntityOnTable(tableName, entityUpdate);
  }

  //method
  private IColumnInfo getColumnDefinitionByTableNameAndColumnName(
    final String tableName,
    final String columnName) {
    return getTableDefinitionByTableName(tableName).getColumnInfoByColumnName(columnName);
  }

  //method
  private ITableInfo getTableDefinitionByTableName(final String tableName) {
    return tableInfos.getStoredFirstOrNull(td -> td.getTableName().equals(tableName));
  }
}
