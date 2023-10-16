//package declaration
package ch.nolix.system.nodedatabaserawdata.databasewriter;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.groupcloseable.CloseController;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.systemapi.rawdatabaseapi.dataadapterapi.IDataWriter;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityHeadDto;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityUpdateDto;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.INewEntityDto;
import ch.nolix.systemapi.rawdatabaseapi.schemainfoapi.ITableInfo;
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
  public DatabaseWriter(final IMutableNode<?> nodeDatabase, final IContainer<ITableInfo> tableInfos) {

    GlobalValidator.assertThat(tableInfos).thatIsNamed("table definitions").isNotNull();

    internalDatabaseWriter = new InternalDatabaseWriter(nodeDatabase);
    this.tableInfos = tableInfos;
  }

  //method
  @Override
  public void deleteMultiReferenceEntries(
      final String tableName,
      final String entityId,
      final String multiReferenceColumnName) {

    final var tableInfo = getTableInfoByTableName(tableName);

    internalDatabaseWriter.deleteEntriesFromMultiReference(
        tableInfo,
        entityId,
        tableInfo.getColumnInfoByColumnName(multiReferenceColumnName));
  }

  //method
  @Override
  public void deleteMultiValueEntries(
      final String tableName,
      final String entityId,
      final String multiValueColumnName) {

    final var tableInfo = getTableInfoByTableName(tableName);

    internalDatabaseWriter.deleteEntriesFromMultiValue(
        tableInfo,
        entityId,
        tableInfo.getColumnInfoByColumnName(multiValueColumnName));
  }

  //method
  @Override
  public void deleteMultiReferenceEntry(
      final String tableName,
      final String entityId,
      final String multiRefereceColumnName,
      final String referencedEntityId) {

    final var tableInfo = getTableInfoByTableName(tableName);

    internalDatabaseWriter.deleteEntryFromMultiReference(
        tableInfo,
        entityId,
        tableInfo.getColumnInfoByColumnName(multiRefereceColumnName),
        referencedEntityId);
  }

  //method
  @Override
  public void deleteMultiValueEntry(
      final String tableName,
      final String entityId,
      final String multiValueColumnName,
      final String entry) {

    final var tableInfo = getTableInfoByTableName(tableName);

    internalDatabaseWriter.deleteEntryFromMultiValue(
        tableInfo,
        entityId,
        tableInfo.getColumnInfoByColumnName(multiValueColumnName),
        entry);
  }

  //method
  @Override
  public void deleteEntity(final String tableName, final IEntityHeadDto entity) {
    internalDatabaseWriter.deleteEntityFromTable(tableName, entity);
  }

  //method
  @Override
  public void expectGivenSchemaTimestamp(ITime schemaTimestamp) {
    internalDatabaseWriter.expectGivenSchemaTimestamp(schemaTimestamp);
  }

  //method
  @Override
  public void expectTableContainsEntity(final String tableName, final String entityId) {
    internalDatabaseWriter.expectTableContainsEntity(tableName, entityId);
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

    final var tableInfo = getTableInfoByTableName(tableName);

    internalDatabaseWriter.insertEntryIntoMultiReference(
        tableInfo,
        entityId,
        tableInfo.getColumnInfoByColumnName(multiReferenceColumnName),
        referencedEntityId);
  }

  //method
  @Override
  public void insertMultiValueEntry(
      final String tableName,
      final String entityId,
      final String multiValueColumnName,
      final String entry) {

    final var tableInfo = getTableInfoByTableName(tableName);

    internalDatabaseWriter.insertEntryIntoMultiValue(
        tableInfo,
        entityId,
        tableInfo.getColumnInfoByColumnName(multiValueColumnName),
        entry);
  }

  //method
  @Override
  public void insertNewEntity(final String tableName, final INewEntityDto newEntity) {
    internalDatabaseWriter.insertEntityIntoTable(getTableInfoByTableName(tableName), newEntity);
  }

  //method
  @Override
  public CloseController getStoredCloseController() {
    return closeController;
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
    internalDatabaseWriter.updateEntityOnTable(getTableInfoByTableName(tableName), entityUpdate);
  }

  //method
  private ITableInfo getTableInfoByTableName(final String tableName) {
    return tableInfos.getStoredFirst(td -> td.getTableName().equals(tableName));
  }
}
