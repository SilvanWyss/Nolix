//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiValue;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiValueEntry;

//class
final class MultiValueSaver {

  //method
  public void saveChangesOfMultiValue(final IMultiValue<?> multiValue, final Database database) {
    for (final var le : multiValue.getStoredLocalEntries()) {
      saveChangeOfMultiValueEntry(le, database);
    }
  }

  //method
  private void saveChangeOfMultiValueEntry(final IMultiValueEntry<?> multiValueEntry, final Database database) {

    final var multiValueEntryState = multiValueEntry.getState();

    switch (multiValueEntryState) {
      case NEW:
        saveMultiValueEntryCreation(multiValueEntry, database);
        break;
      case DELETED:
        saveMultiValueEntryDeletion(multiValueEntry, database);
        break;
      default:
        throw InvalidArgumentException.forArgumentNameAndArgument(
          "state of multi value",
          multiValueEntryState);
    }
  }

  //method
  private void saveMultiValueEntryCreation(final IMultiValueEntry<?> multiValueEntry, final Database database) {

    final var entity = multiValueEntry.getStoredParentMultiValue().getStoredParentEntity();

    database.internalGetRefDataAndSchemaAdapter().insertMultiValueEntry(
      entity.getParentTableName(),
      entity.getId(),
      multiValueEntry.getStoredParentMultiValue().getName(),
      multiValueEntry.getStoredValue().toString());
  }

  //method
  private void saveMultiValueEntryDeletion(final IMultiValueEntry<?> multiValueEntry, final Database database) {

    final var entity = multiValueEntry.getStoredParentMultiValue().getStoredParentEntity();

    database.internalGetRefDataAndSchemaAdapter().deleteMultiValueEntry(
      entity.getParentTableName(),
      entity.getId(),
      multiValueEntry.getStoredParentMultiValue().getName(),
      multiValueEntry.toString());
  }
}
