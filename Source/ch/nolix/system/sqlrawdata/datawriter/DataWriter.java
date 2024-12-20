package ch.nolix.system.sqlrawdata.datawriter;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.core.sql.connectionpool.SqlConnectionPool;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.sqlapi.connectionapi.ISqlConnection;
import ch.nolix.systemapi.rawdataapi.dataadapterapi.IDataWriter;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IEntityHeadDto;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IEntityUpdateDto;
import ch.nolix.systemapi.rawdataapi.datadtoapi.INewEntityDto;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.IColumnInfo;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.ITableInfo;
import ch.nolix.systemapi.sqlrawdataapi.sqlsyntaxapi.ISqlSyntaxProvider;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public final class DataWriter implements IDataWriter {

  private final CloseController closeController = CloseController.forElement(this);

  private final InternalDataWriter internalDataWriter;

  private final IContainer<ITableInfo> tableInfos;

  private DataWriter(
    final String databaseName,
    final ISqlConnection sqlConnection,
    final IContainer<ITableInfo> tableInfos,
    final ISqlSyntaxProvider sqlSyntaxProvider) {

    GlobalValidator.assertThat(tableInfos).thatIsNamed("table definitions").isNotNull();

    internalDataWriter = new InternalDataWriter(databaseName, sqlConnection, sqlSyntaxProvider);
    this.tableInfos = tableInfos;

    createCloseDependencyTo(sqlConnection);
  }

  public static DataWriter forDatabaseWithGivenNameUsingConnectionFromGivenPoolAndTableInfosAndSqlSyntaxProvider(
    final String databaseName,
    final SqlConnectionPool sqlConnectionPool,
    final IContainer<ITableInfo> tableInfos,
    final ISqlSyntaxProvider sqlSyntaxProvider) {
    return new DataWriter(databaseName, sqlConnectionPool.borrowResource(), tableInfos,
      sqlSyntaxProvider);
  }

  @Override
  public void deleteEntity(final String tableName, final IEntityHeadDto entity) {
    internalDataWriter.deleteEntity(tableName, entity);
  }

  @Override
  public void deleteMultiBackReferenceEntry(
    final String tableName,
    final String entityId,
    final String multiBackReferenceColumnId,
    final String backReferencedEntityId) {
    internalDataWriter.deleteMultiBackReferenceEntry(entityId, multiBackReferenceColumnId, backReferencedEntityId);
  }

  @Override
  public void deleteMultiReferenceEntries(
    final String tableName,
    final String entityId,
    final String multiReferenceColumnName) {
    internalDataWriter.deleteEntriesFromMultiReference(
      entityId,
      getColumnDefinitionByTableNameAndColumnName(tableName, multiReferenceColumnName).getColumnId());
  }

  @Override
  public void deleteMultiReferenceEntry(
    final String tableName,
    final String entityId,
    final String multiRefereceColumnName,
    final String referencedEntityId) {
    internalDataWriter.deleteEntryFromMultiReference(
      entityId,
      getColumnDefinitionByTableNameAndColumnName(tableName, multiRefereceColumnName).getColumnId(),
      referencedEntityId);
  }

  @Override
  public void deleteMultiValueEntries(
    final String tableName,
    final String entityId,
    final String multiValueColumnName) {
    internalDataWriter.deleteEntriesFromMultiValue(
      entityId,
      getColumnDefinitionByTableNameAndColumnName(tableName, multiValueColumnName).getColumnId());
  }

  @Override
  public void deleteMultiValueEntry(
    final String tableName,
    final String entityId,
    final String multiValueColumnName,
    final String entry) {
    internalDataWriter.deleteEntryFromMultiValue(
      entityId,
      getColumnDefinitionByTableNameAndColumnName(tableName, multiValueColumnName).getColumnId(),
      entry);
  }

  @Override
  public void expectGivenSchemaTimestamp(final ITime schemaTimestamp) {
    internalDataWriter.expectGivenSchemaTimestamp(schemaTimestamp);
  }

  @Override
  public void expectTableContainsEntity(final String tableName, final String entityId) {
    internalDataWriter.expectTableContainsEntity(tableName, entityId);
  }

  @Override
  public CloseController getStoredCloseController() {
    return closeController;
  }

  @Override
  public int getSaveCount() {
    return internalDataWriter.getSaveCount();
  }

  @Override
  public boolean hasChanges() {
    return internalDataWriter.hasChanges();
  }

  @Override
  public void insertEntity(final String tableName, final INewEntityDto newEntity) {
    internalDataWriter.insertEntity(tableName, newEntity);
  }

  @Override
  public void insertMultiBackReferenceEntry(
    final String tableName,
    final String entityId,
    final String multiBackReferenceColumnId,
    final String backReferencedEntityId) {
    internalDataWriter.insertEntryIntoMultiBackReference(entityId, multiBackReferenceColumnId, backReferencedEntityId);
  }

  @Override
  public void insertMultiReferenceEntry(
    final String tableName,
    final String entityId,
    final String multiReferenceColumnName,
    final String referencedEntityId) {
    internalDataWriter.insertEntryIntoMultiReference(
      entityId,
      getColumnDefinitionByTableNameAndColumnName(tableName, multiReferenceColumnName).getColumnId(),
      referencedEntityId);
  }

  @Override
  public void insertMultiValueEntry(
    final String tableName,
    final String entityId,
    final String multiValueColumnName,
    final String entry) {
    internalDataWriter.insertEntryIntoMultiValue(
      entityId,
      getColumnDefinitionByTableNameAndColumnName(tableName, multiValueColumnName).getColumnId(),
      entry);
  }

  @Override
  public void noteClose() {
    //Does nothing.
  }

  @Override
  public void reset() {
    internalDataWriter.reset();
  }

  @Override
  public void saveChanges() {
    internalDataWriter.saveChangesAndReset();
  }

  @Override
  public void setEntityAsUpdated(final String tableName, final IEntityHeadDto entity) {
    internalDataWriter.setEntityAsUpdated(tableName, entity);
  }

  @Override
  public void updateEntity(final String tableName, final IEntityUpdateDto entityUpdate) {
    internalDataWriter.updateEntityOnTable(tableName, entityUpdate);
  }

  private IColumnInfo getColumnDefinitionByTableNameAndColumnName(
    final String tableName,
    final String columnName) {
    return getTableDefinitionByTableName(tableName).getColumnInfoByColumnName(columnName);
  }

  private ITableInfo getTableDefinitionByTableName(final String tableName) {
    return tableInfos.getStoredFirst(td -> td.getTableName().equals(tableName));
  }
}
