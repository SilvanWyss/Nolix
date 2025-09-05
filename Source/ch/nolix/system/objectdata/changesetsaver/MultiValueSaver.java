package ch.nolix.system.objectdata.changesetsaver;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdata.middatamodelmapper.MultiValueEntryDtoMapper;
import ch.nolix.systemapi.middata.adapter.IDataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdata.middatamodelmapper.IMultiValueEntryDtoMapper;
import ch.nolix.systemapi.objectdata.model.IMultiValueField;
import ch.nolix.systemapi.objectdata.model.IMultiValueFieldEntry;

public final class MultiValueSaver {
  private static final IMultiValueEntryDtoMapper MULTI_VALUE_ENTRY_DTO_MAPPER = new MultiValueEntryDtoMapper();

  public void saveChangesOfMultiValue(
    final IMultiValueField<?> multiValue,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {
    for (final var e : multiValue.getStoredNewAndDeletedEntries()) {
      saveChangeOfMultiValueEntry(e, dataAndSchemaAdapter);
    }
  }

  private void saveChangeOfMultiValueEntry(
    final IMultiValueFieldEntry<?> multiValueEntry,
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
    final IMultiValueFieldEntry<?> multiValueEntry,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {
    final var multiValueEntryDto = MULTI_VALUE_ENTRY_DTO_MAPPER.mapMultiValueEntryToMultiValueEntryDto(multiValueEntry);

    dataAndSchemaAdapter.insertMultiValueEntry(multiValueEntryDto);
  }

  private void saveMultiValueEntryDeletion(
    final IMultiValueFieldEntry<?> multiValueEntry,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {
    final var multiValueEntryDto = MULTI_VALUE_ENTRY_DTO_MAPPER.mapMultiValueEntryToMultiValueEntryDto(multiValueEntry);

    dataAndSchemaAdapter.deleteMultiValueEntry(multiValueEntryDto);
  }
}
