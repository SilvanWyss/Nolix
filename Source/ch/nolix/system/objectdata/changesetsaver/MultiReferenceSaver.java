//package declaration
package ch.nolix.system.objectdata.changesetsaver;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiReferenceEntry;
import ch.nolix.systemapi.rawdataapi.dataandschemaadapterapi.IDataAndSchemaAdapter;

//class
final class MultiReferenceSaver {

  //method
  public void saveChangesOfMultiReference(
    final IMultiReference<?> multiReference,
    final IDataAndSchemaAdapter dataAndSchemaAdapter) {
    for (final var le : multiReference.getStoredLocalEntries()) {
      saveChangeOfMultiReferenceEntry(le, dataAndSchemaAdapter);
    }
  }

  //method
  private void saveChangeOfMultiReferenceEntry(
    final IMultiReferenceEntry<?> multiReferenceEntry,
    final IDataAndSchemaAdapter dataAndSchemaAdapter) {

    final var multiReferenceEntryState = multiReferenceEntry.getState();

    switch (multiReferenceEntryState) {
      case NEW:
        saveMultiReferenceEntryCreation(multiReferenceEntry, dataAndSchemaAdapter);
        break;
      case LOADED:
        break;
      case DELETED:
        saveMultiReferenceEntryDeletion(multiReferenceEntry, dataAndSchemaAdapter);
        break;
      default:
        throw InvalidArgumentException.forArgumentNameAndArgument(
          "state of multi reference",
          multiReferenceEntryState);
    }
  }

  //method
  private void saveMultiReferenceEntryCreation(
    final IMultiReferenceEntry<?> multiReferenceEntry,
    final IDataAndSchemaAdapter dataAndSchemaAdapter) {

    final var entity = multiReferenceEntry.getStoredParentMultiReference().getStoredParentEntity();

    dataAndSchemaAdapter.insertMultiReferenceEntry(
      entity.getParentTableName(),
      entity.getId(),
      multiReferenceEntry.getStoredParentMultiReference().getName(),
      multiReferenceEntry.getReferencedEntityId());
  }

  //method
  private void saveMultiReferenceEntryDeletion(
    final IMultiReferenceEntry<?> multiReferenceEntry,
    final IDataAndSchemaAdapter dataAndSchemaAdapter) {

    final var entity = multiReferenceEntry.getStoredParentMultiReference().getStoredParentEntity();

    dataAndSchemaAdapter.deleteMultiReferenceEntry(
      entity.getParentTableName(),
      entity.getId(),
      multiReferenceEntry.getStoredParentMultiReference().getName(),
      multiReferenceEntry.getReferencedEntityId());
  }
}
