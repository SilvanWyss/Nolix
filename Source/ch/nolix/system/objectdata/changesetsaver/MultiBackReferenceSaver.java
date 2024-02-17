//package declaration
package ch.nolix.system.objectdata.changesetsaver;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiBackReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiBackReferenceEntry;
import ch.nolix.systemapi.rawdataapi.dataandschemaadapterapi.IDataAndSchemaAdapter;

//class
public final class MultiBackReferenceSaver {

  //method
  public void saveMultiBackReference(
    final IMultiBackReference<? extends IEntity> multiBackReference,
    final IDataAndSchemaAdapter dataAndSchemaAdapter) {
    for (final var le : multiBackReference.getStoredLocalEntries()) {
      saveMultiBackReferenceEntry(le, dataAndSchemaAdapter);
    }
  }

  //method
  private void saveMultiBackReferenceEntry(
    final IMultiBackReferenceEntry<?> multiBackReferenceEntry,
    final IDataAndSchemaAdapter dataAndSchemaAdapter) {

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
        throw InvalidArgumentException.forArgumentNameAndArgument(
          "state of multi reference",
          multiReferenceEntryState);
    }
  }

  //method
  private void insertMultiBackReferenceEntry(
    final IMultiBackReferenceEntry<?> multiBackReferenceEntry,
    final IDataAndSchemaAdapter dataAndSchemaAdapter) {

    final var multiBackReference = multiBackReferenceEntry.getStoredParentMultiBackReference();
    final var multiBackReferenceColumn = multiBackReference.getStoredParentColumn();
    final var entity = multiBackReference.getStoredParentEntity();

    dataAndSchemaAdapter.insertMultiBackReferenceEntry(
      entity.getParentTableName(),
      entity.getId(),
      multiBackReferenceColumn.getId(),
      multiBackReferenceEntry.getBackReferencedEntityId());
  }

  //method
  private void deleteMultiBackReferenceEntry(
    final IMultiBackReferenceEntry<?> multiBackReferenceEntry,
    final IDataAndSchemaAdapter dataAndSchemaAdapter) {

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
