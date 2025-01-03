package ch.nolix.system.noderawdata.datawriter;

import java.util.function.Consumer;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.systemapi.rawdataapi.dto.EntityCreationDto;
import ch.nolix.systemapi.rawdataapi.dto.EntityDeletionDto;
import ch.nolix.systemapi.rawdataapi.dto.EntityUpdateDto;
import ch.nolix.systemapi.rawdataapi.schemaviewapi.IColumnView;
import ch.nolix.systemapi.rawdataapi.schemaviewapi.ITableView;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

final class InternalDataWriter {

  private static final DatabaseUpdater DATABASE_UPDATER = new DatabaseUpdater();

  private int saveCount;

  private final IMutableNode<?> nodeDatabase;

  private final LinkedList<Consumer<IMutableNode<?>>> changeActions = LinkedList.createEmpty();

  public InternalDataWriter(final IMutableNode<?> nodeDatabase) {

    GlobalValidator.assertThat(nodeDatabase).thatIsNamed("node database").isNotNull();

    this.nodeDatabase = nodeDatabase;
  }

  public void deleteEntriesFromMultiReference(
    final ITableView tableView,
    final String entityId,
    final IColumnView multiReferenceColumnInfo) {
    addChangeAction(
      d -> DATABASE_UPDATER.deleteEntriesFromMultiReference(d, tableView, entityId, multiReferenceColumnInfo));
  }

  public void deleteEntriesFromMultiValue(
    final ITableView tableView,
    final String entityId,
    final IColumnView multiValueColumnInfo) {
    addChangeAction(
      d -> DATABASE_UPDATER.deleteEntriesFromMultiValue(d, tableView, entityId, multiValueColumnInfo));
  }

  public void deleteEntryFromMultiReference(
    final ITableView tableView,
    final String entityId,
    final IColumnView multiReferenceColumnInfo,
    final String referencedEntityId) {
    addChangeAction(
      d -> DATABASE_UPDATER.deleteEntryFromMultiReference(
        d,
        tableView,
        entityId,
        multiReferenceColumnInfo,
        referencedEntityId));
  }

  public void deleteEntryFromMultiValue(
    final ITableView tableView,
    final String entityId,
    final IColumnView multiValueColumnInfo,
    final String entry) {
    addChangeAction(
      d -> DATABASE_UPDATER.deleteEntryFromMultiValue(d, tableView, entityId, multiValueColumnInfo, entry));
  }

  public void deleteEntityFromTable(final String tableName, final EntityDeletionDto entity) {
    addChangeAction(d -> DATABASE_UPDATER.deleteEntityFromTable(d, tableName, entity));
  }

  public void deleteMultiBackReferenceEntry(
    final ITableView tableView,
    final String entityId,
    final IColumnView multiBackReferenceColumnInfo,
    final String backReferencedEntityId) {

    final Consumer<IMutableNode<?>> changeAction = d -> DATABASE_UPDATER.deleteMultiBackReferenceEntry(
      d,
      tableView,
      entityId,
      multiBackReferenceColumnInfo,
      backReferencedEntityId);

    addChangeAction(changeAction);
  }

  public void expectGivenSchemaTimestamp(ITime schemaTimestamp) {
    addChangeAction(d -> DATABASE_UPDATER.expectGivenSchemaTimestamp(d, schemaTimestamp));
  }

  public void expectTableContainsEntity(final String tableName, final String entityId) {
    addChangeAction(d -> DATABASE_UPDATER.expectTableContainsEntity(d, tableName, entityId));
  }

  public int getSaveCount() {
    return saveCount;
  }

  public boolean hasChanges() {
    return changeActions.containsAny();
  }

  public void insertEntryIntoMultiBackReference(
    final ITableView tableView,
    final String entityId,
    final IColumnView multiBackReferenceColumnInfo,
    final String backReferencedEntityId) {

    final Consumer<IMutableNode<?>> changeAction = d -> DATABASE_UPDATER.insertEntryIntoMultiBackReference(
      d,
      tableView,
      entityId,
      multiBackReferenceColumnInfo,
      backReferencedEntityId);

    addChangeAction(changeAction);
  }

  public void insertEntryIntoMultiReference(
    final ITableView tableView,
    final String entityId,
    final IColumnView multiReferenceColumnInfo,
    final String referencedEntityId) {
    addChangeAction(
      d -> DATABASE_UPDATER.insertEntryIntoMultiReference(
        d,
        tableView,
        entityId,
        multiReferenceColumnInfo,
        referencedEntityId));
  }

  public void insertEntryIntoMultiValue(
    final ITableView tableView,
    final String entityId,
    final IColumnView multiValueColumnInfo,
    final String entry) {
    addChangeAction(
      d -> DATABASE_UPDATER.insertEntryIntoMultiValue(d, tableView, entityId, multiValueColumnInfo, entry));
  }

  public void insertEntityIntoTable(final ITableView tableView, final EntityCreationDto newEntity) {
    addChangeAction(d -> DATABASE_UPDATER.insertEntityIntoTable(d, tableView, newEntity));
  }

  public void reset() {
    changeActions.clear();
  }

  public void saveChangesAndReset() {
    try {
      nodeDatabase.setChildNodes(createNodeDatabaseWithChanges().getStoredChildNodes());
      saveCount++;
    } finally {
      reset();
    }
  }

  public void updateEntityOnTable(final ITableView tableView, EntityUpdateDto entityUpdate) {
    addChangeAction(d -> DATABASE_UPDATER.updateEntityOnTable(d, tableView, entityUpdate));
  }

  private void addChangeAction(final Consumer<IMutableNode<?>> changeAction) {
    changeActions.addAtEnd(changeAction);
  }

  private IMutableNode<?> createNodeDatabaseWithChanges() {

    final var newNodeDatabase = MutableNode.fromNode(nodeDatabase);
    for (final var ca : changeActions) {
      ca.accept(newNodeDatabase);
    }

    return newNodeDatabase;
  }
}
