package ch.nolix.system.objectdata.changesetsaver;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.middataapi.adapterapi.IDataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdataapi.modelapi.IMultiValue;
import ch.nolix.systemapi.objectdataapi.modelapi.IMultiValueEntry;

final class MultiValueSaver {

  public void saveChangesOfMultiValue(
    final IMultiValue<?> multiValue,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {
    for (final var e : multiValue.getStoredNewAndDeletedEntries()) {
      saveChangeOfMultiValueEntry(e, dataAndSchemaAdapter);
    }
  }

  private void saveChangeOfMultiValueEntry(
    final IMultiValueEntry<?> multiValueEntry,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {

    final var multiValueEntryState = multiValueEntry.getState();

    switch (multiValueEntryState) {
      case NEW:
        saveMultiValueEntryCreation(multiValueEntry, dataAndSchemaAdapter);
        break;
      case DELETED:
        saveMultiValueEntryDeletion(multiValueEntry, dataAndSchemaAdapter);
        break;
      default:
        throw InvalidArgumentException.forArgumentAndArgumentName(multiValueEntryState, "state of multi value");
    }
  }

  private void saveMultiValueEntryCreation(
    final IMultiValueEntry<?> multiValueEntry,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {

    final var entity = multiValueEntry.getStoredParentMultiValue().getStoredParentEntity();

    dataAndSchemaAdapter.insertMultiValueEntry(
      entity.getParentTableName(),
      entity.getId(),
      multiValueEntry.getStoredParentMultiValue().getName(),
      multiValueEntry.getStoredValue().toString());
  }

  private void saveMultiValueEntryDeletion(
    final IMultiValueEntry<?> multiValueEntry,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {

    final var entity = multiValueEntry.getStoredParentMultiValue().getStoredParentEntity();

    dataAndSchemaAdapter.deleteMultiValueEntry(
      entity.getParentTableName(),
      entity.getId(),
      multiValueEntry.getStoredParentMultiValue().getName(),
      multiValueEntry.toString());
  }
}
