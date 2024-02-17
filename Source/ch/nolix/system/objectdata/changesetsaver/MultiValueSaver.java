//package declaration
package ch.nolix.system.objectdata.changesetsaver;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiValue;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiValueEntry;
import ch.nolix.systemapi.rawdataapi.dataandschemaadapterapi.IDataAndSchemaAdapter;

//class
final class MultiValueSaver {

  //method
  public void saveChangesOfMultiValue(
    final IMultiValue<?> multiValue,
    final IDataAndSchemaAdapter dataAndSchemaAdapter) {
    for (final var e : multiValue.getStoredNewAndDeletedEntries()) {
      saveChangeOfMultiValueEntry(e, dataAndSchemaAdapter);
    }
  }

  //method
  private void saveChangeOfMultiValueEntry(
    final IMultiValueEntry<?> multiValueEntry,
    final IDataAndSchemaAdapter dataAndSchemaAdapter) {

    final var multiValueEntryState = multiValueEntry.getState();

    switch (multiValueEntryState) {
      case NEW:
        saveMultiValueEntryCreation(multiValueEntry, dataAndSchemaAdapter);
        break;
      case DELETED:
        saveMultiValueEntryDeletion(multiValueEntry, dataAndSchemaAdapter);
        break;
      default:
        throw InvalidArgumentException.forArgumentNameAndArgument(
          "state of multi value",
          multiValueEntryState);
    }
  }

  //method
  private void saveMultiValueEntryCreation(
    final IMultiValueEntry<?> multiValueEntry,
    final IDataAndSchemaAdapter dataAndSchemaAdapter) {

    final var entity = multiValueEntry.getStoredParentMultiValue().getStoredParentEntity();

    dataAndSchemaAdapter.insertMultiValueEntry(
      entity.getParentTableName(),
      entity.getId(),
      multiValueEntry.getStoredParentMultiValue().getName(),
      multiValueEntry.getStoredValue().toString());
  }

  //method
  private void saveMultiValueEntryDeletion(
    final IMultiValueEntry<?> multiValueEntry,
    final IDataAndSchemaAdapter dataAndSchemaAdapter) {

    final var entity = multiValueEntry.getStoredParentMultiValue().getStoredParentEntity();

    dataAndSchemaAdapter.deleteMultiValueEntry(
      entity.getParentTableName(),
      entity.getId(),
      multiValueEntry.getStoredParentMultiValue().getName(),
      multiValueEntry.toString());
  }
}
