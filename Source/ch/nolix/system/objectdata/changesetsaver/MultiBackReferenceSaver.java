package ch.nolix.system.objectdata.changesetsaver;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.middataapi.adapterapi.IDataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.IMultiBackReference;
import ch.nolix.systemapi.objectdataapi.modelapi.IMultiBackReferenceEntry;

public final class MultiBackReferenceSaver {

  public void saveMultiBackReference(
    final IMultiBackReference<? extends IEntity> multiBackReference,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {
    for (final var le : multiBackReference.getStoredNewAndDeletedEntries()) {
      saveMultiBackReferenceEntry(le, dataAndSchemaAdapter);
    }
  }

  private void saveMultiBackReferenceEntry(
    final IMultiBackReferenceEntry<?> multiBackReferenceEntry,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {

    final var multiReferenceEntryState = multiBackReferenceEntry.getState();

    switch (multiReferenceEntryState) {
      case NEW:
        insertMultiBackReferenceEntry(multiBackReferenceEntry, dataAndSchemaAdapter);
        break;
      case LOADED:
        break;
      case DELETED:
        deleteMultiBackReferenceEntry(multiBackReferenceEntry, dataAndSchemaAdapter);
        break;
      default:
        throw //
        InvalidArgumentException.forArgumentAndArgumentName(multiReferenceEntryState, "state of multi reference");
    }
  }

  private void insertMultiBackReferenceEntry(
    final IMultiBackReferenceEntry<?> multiBackReferenceEntry,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {

    final var multiBackReference = multiBackReferenceEntry.getStoredParentMultiBackReference();
    final var multiBackReferenceColumn = multiBackReference.getStoredParentColumn();
    final var entity = multiBackReference.getStoredParentEntity();

    dataAndSchemaAdapter.insertMultiBackReferenceEntry(
      entity.getParentTableName(),
      entity.getId(),
      multiBackReferenceColumn.getId(),
      multiBackReferenceEntry.getBackReferencedEntityId());
  }

  private void deleteMultiBackReferenceEntry(
    final IMultiBackReferenceEntry<?> multiBackReferenceEntry,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {

    final var multiBackReference = multiBackReferenceEntry.getStoredParentMultiBackReference();
    final var multiBackReferenceColumn = multiBackReference.getStoredParentColumn();
    final var entity = multiBackReference.getStoredParentEntity();

    dataAndSchemaAdapter.deleteMultiBackReferenceEntry(
      entity.getParentTableName(),
      entity.getId(),
      multiBackReferenceColumn.getId(),
      multiBackReferenceEntry.getBackReferencedEntityId());
  }
}
