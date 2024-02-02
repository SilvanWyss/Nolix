//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiReferenceEntry;

//class
final class MultiReferenceSaver {

  //method
  public void saveChangesOfMultiReference(final IMultiReference<?> multiReference, final Database database) {
    for (final var le : multiReference.getStoredLocalEntries()) {
      saveChangeOfMultiReferenceEntry(le, database);
    }
  }

  //method
  private void saveChangeOfMultiReferenceEntry(
    final IMultiReferenceEntry<?> multiReferenceEntry,
    final Database database) {

    final var multiReferenceEntryState = multiReferenceEntry.getState();

    switch (multiReferenceEntryState) {
      case NEW:
        saveMultiReferenceEntryCreation(multiReferenceEntry, database);
        break;
      case LOADED:
        break;
      case DELETED:
        saveMultiReferenceEntryDeletion(multiReferenceEntry, database);
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
    final Database database) {

    final var entity = multiReferenceEntry.getStoredParentMultiReference().getStoredParentEntity();

    database.internalGetRefDataAndSchemaAdapter().insertMultiReferenceEntry(
      entity.getParentTableName(),
      entity.getId(),
      multiReferenceEntry.getStoredParentMultiReference().getName(),
      multiReferenceEntry.getReferencedEntityId());
  }

  //method
  private void saveMultiReferenceEntryDeletion(
    final IMultiReferenceEntry<?> multiReferenceEntry,
    final Database database) {

    final var entity = multiReferenceEntry.getStoredParentMultiReference().getStoredParentEntity();

    database.internalGetRefDataAndSchemaAdapter().deleteMultiReferenceEntry(
      entity.getParentTableName(),
      entity.getId(),
      multiReferenceEntry.getStoredParentMultiReference().getName(),
      multiReferenceEntry.getReferencedEntityId());
  }
}
