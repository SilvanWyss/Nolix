//package declaration
package ch.nolix.system.nodedatabaserawdata.databasewriter;

import java.util.function.Consumer;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityHeadDto;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityUpdateDto;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.INewEntityDto;
import ch.nolix.systemapi.rawdatabaseapi.schemainfoapi.IColumnInfo;
import ch.nolix.systemapi.rawdatabaseapi.schemainfoapi.ITableInfo;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//class
final class InternalDatabaseWriter {

  //constant
  private static final DatabaseUpdater DATABASE_UPDATER = new DatabaseUpdater();

  //attribute
  private int saveCount;

  //attribute
  private final IMutableNode<?> nodeDatabase;

  //multi-attribute
  private final LinkedList<Consumer<IMutableNode<?>>> changeActions = new LinkedList<>();

  //constructor
  public InternalDatabaseWriter(final IMutableNode<?> nodeDatabase) {

    GlobalValidator.assertThat(nodeDatabase).thatIsNamed("node database").isNotNull();

    this.nodeDatabase = nodeDatabase;
  }

  //method
  public void deleteEntriesFromMultiReference(
    final ITableInfo tableInfo,
    final String entityId,
    final IColumnInfo multiReferenceColumnInfo) {
    addChangeAction(
      d -> DATABASE_UPDATER.deleteEntriesFromMultiReference(d, tableInfo, entityId, multiReferenceColumnInfo));
  }

  //method
  public void deleteEntriesFromMultiValue(
    final ITableInfo tableInfo,
    final String entityId,
    final IColumnInfo multiValueColumnInfo) {
    addChangeAction(
      d -> DATABASE_UPDATER.deleteEntriesFromMultiValue(d, tableInfo, entityId, multiValueColumnInfo));
  }

  //method
  public void deleteEntryFromMultiReference(
    final ITableInfo tableInfo,
    final String entityId,
    final IColumnInfo multiReferenceColumnInfo,
    final String referencedEntityId) {
    addChangeAction(
      d -> DATABASE_UPDATER.deleteEntryFromMultiReference(
        d,
        tableInfo,
        entityId,
        multiReferenceColumnInfo,
        referencedEntityId));
  }

  //method
  public void deleteEntryFromMultiValue(
    final ITableInfo tableInfo,
    final String entityId,
    final IColumnInfo multiValueColumnInfo,
    final String entry) {
    addChangeAction(
      d -> DATABASE_UPDATER.deleteEntryFromMultiValue(d, tableInfo, entityId, multiValueColumnInfo, entry));
  }

  //method
  public void deleteEntityFromTable(final String tableName, final IEntityHeadDto entity) {
    addChangeAction(d -> DATABASE_UPDATER.deleteEntityFromTable(d, tableName, entity));
  }

  //method
  public void expectGivenSchemaTimestamp(ITime schemaTimestamp) {
    addChangeAction(d -> DATABASE_UPDATER.expectGivenSchemaTimestamp(d, schemaTimestamp));
  }

  //method
  public void expectTableContainsEntity(final String tableName, final String entityId) {
    addChangeAction(d -> DATABASE_UPDATER.expectTableContainsEntity(d, tableName, entityId));
  }

  //method
  public int getSaveCount() {
    return saveCount;
  }

  //method
  public boolean hasChanges() {
    return changeActions.containsAny();
  }

  //method
  public void insertEntryIntoMultiReference(
    final ITableInfo tableInfo,
    final String entityId,
    final IColumnInfo multiReferenceColumnInfo,
    final String referencedEntityId) {
    addChangeAction(
      d -> DATABASE_UPDATER.insertEntryIntoMultiReference(
        d,
        tableInfo,
        entityId,
        multiReferenceColumnInfo,
        referencedEntityId));
  }

  //method
  public void insertEntryIntoMultiValue(
    final ITableInfo tableInfo,
    final String entityId,
    final IColumnInfo multiValueColumnInfo,
    final String entry) {
    addChangeAction(
      d -> DATABASE_UPDATER.insertEntryIntoMultiValue(d, tableInfo, entityId, multiValueColumnInfo, entry));
  }

  //method
  public void insertEntityIntoTable(final ITableInfo tableInfo, final INewEntityDto newEntity) {
    addChangeAction(d -> DATABASE_UPDATER.insertEntityIntoTable(d, tableInfo, newEntity));
  }

  //methods
  public void reset() {
    changeActions.clear();
  }

  //method
  public void saveChangesAndReset() {
    try {
      nodeDatabase.setChildNodes(createNodeDatabaseWithChanges().getStoredChildNodes());
      saveCount++;
    } finally {
      reset();
    }
  }

  //method
  public void setEntityAsUpdated(final String tableName, final IEntityHeadDto entity) {
    addChangeAction(d -> DATABASE_UPDATER.setEntityAsUpdated(d, tableName, entity));
  }

  //method
  public void updateEntityOnTable(final ITableInfo tableInfo, IEntityUpdateDto entityUpdate) {
    addChangeAction(d -> DATABASE_UPDATER.updateEntityOnTable(d, tableInfo, entityUpdate));
  }

  //method
  private void addChangeAction(final Consumer<IMutableNode<?>> changeAction) {
    changeActions.addAtEnd(changeAction);
  }

  //method
  private IMutableNode<?> createNodeDatabaseWithChanges() {

    final var newNodeDatabase = MutableNode.fromNode(nodeDatabase);
    for (final var ca : changeActions) {
      ca.accept(newNodeDatabase);
    }

    return newNodeDatabase;
  }
}
