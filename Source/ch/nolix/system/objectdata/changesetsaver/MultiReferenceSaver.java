//package declaration
package ch.nolix.system.objectdata.changesetsaver;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiReferenceEntry;
import ch.nolix.systemapi.rawdataapi.dataandschemaadapterapi.IDataAndSchemaAdapter;

//class
public final class MultiReferenceSaver {

  //method
  public void saveMultiReference(
    final IMultiReference<?> multiReference,
    final IDataAndSchemaAdapter dataAndSchemaAdapter) {
    for (final var le : multiReference.getStoredNewAndDeletedEntries()) {
      saveMultiReferenceEntry(le, dataAndSchemaAdapter);
    }
  }

  //method
  private void saveMultiReferenceEntry(
    final IMultiReferenceEntry<?> multiReferenceEntry,
    final IDataAndSchemaAdapter dataAndSchemaAdapter) {

    final var state = multiReferenceEntry.getState();

    switch (state) {
      case NEW:
        insertMultiReferenceEntry(multiReferenceEntry, dataAndSchemaAdapter);
        break;
      case LOADED:
        break;
      case DELETED:
        deleteMultiReferenceEntry(multiReferenceEntry, dataAndSchemaAdapter);
        break;
      default:
        throw InvalidArgumentException.forArgumentNameAndArgument("state of multi reference", state);
    }
  }

  //method
  private void insertMultiReferenceEntry(
    final IMultiReferenceEntry<?> multiReferenceEntry,
    final IDataAndSchemaAdapter dataAndSchemaAdapter) {

    final var multiReference = multiReferenceEntry.getStoredParentMultiReference();
    final var entity = multiReference.getStoredParentEntity();

    dataAndSchemaAdapter.insertMultiReferenceEntry(
      entity.getParentTableName(),
      entity.getId(),
      multiReference.getName(),
      multiReferenceEntry.getReferencedEntityId());
  }

  //method
  private void deleteMultiReferenceEntry(
    final IMultiReferenceEntry<?> multiReferenceEntry,
    final IDataAndSchemaAdapter dataAndSchemaAdapter) {

    final var multiReference = multiReferenceEntry.getStoredParentMultiReference();
    final var entity = multiReference.getStoredParentEntity();

    dataAndSchemaAdapter.deleteMultiReferenceEntry(
      entity.getParentTableName(),
      entity.getId(),
      multiReference.getName(),
      multiReferenceEntry.getReferencedEntityId());
  }
}
