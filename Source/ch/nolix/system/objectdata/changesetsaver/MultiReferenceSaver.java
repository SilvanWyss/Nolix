package ch.nolix.system.objectdata.changesetsaver;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdata.middatamodelmapper.MultiReferenceEntryDeletionDtoMapper;
import ch.nolix.system.objectdata.middatamodelmapper.MultiReferenceEntryDtoMapper;
import ch.nolix.systemapi.middata.adapter.IDataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdata.middatamodelmapper.IMultiReferenceEntryDeletionDtoMapper;
import ch.nolix.systemapi.objectdata.middatamodelmapper.IMultiReferenceEntryDtoMapper;
import ch.nolix.systemapi.objectdata.model.IMultiReference;
import ch.nolix.systemapi.objectdata.model.IMultiReferenceEntry;

public final class MultiReferenceSaver {
  private static final IMultiReferenceEntryDtoMapper MULTI_REFERENCE_ENTRY_DTO_MAPPER = //
  new MultiReferenceEntryDtoMapper();

  private static final IMultiReferenceEntryDeletionDtoMapper MULTI_REFERENCE_ENTRY_DELETION_DTO_MAPPER = //
  new MultiReferenceEntryDeletionDtoMapper();

  public void saveMultiReference(
    final IMultiReference<?> multiReference,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {
    for (final var le : multiReference.getStoredNewAndDeletedEntries()) {
      saveMultiReferenceEntry(le, dataAndSchemaAdapter);
    }
  }

  private void saveMultiReferenceEntry(
    final IMultiReferenceEntry<?> multiReferenceEntry,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {
    final var state = multiReferenceEntry.getState();

    switch (state) {
      case NEW:
        insertMultiReferenceEntry(multiReferenceEntry, dataAndSchemaAdapter);
        break;
      case UNEDITED:
        break;
      case DELETED:
        deleteMultiReferenceEntry(multiReferenceEntry, dataAndSchemaAdapter);
        break;
      default:
        throw InvalidArgumentException.forArgumentAndArgumentName(state, "state of multi reference");
    }
  }

  private void insertMultiReferenceEntry(
    final IMultiReferenceEntry<?> multiReferenceEntry,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {
    final var multiReferenceEntryDto = //
    MULTI_REFERENCE_ENTRY_DTO_MAPPER.mapMultiReferenceEntryToMultiReferenceEntryDto(multiReferenceEntry);

    dataAndSchemaAdapter.insertMultiReferenceEntry(multiReferenceEntryDto);
  }

  private void deleteMultiReferenceEntry(
    final IMultiReferenceEntry<?> multiReferenceEntry,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {
    final var multiReferenceEntryDeletionDto = //
    MULTI_REFERENCE_ENTRY_DELETION_DTO_MAPPER.mapMultiReferenceEntryToMultiReferenceEntryDeletionDto(
      multiReferenceEntry);

    dataAndSchemaAdapter.deleteMultiReferenceEntry(multiReferenceEntryDeletionDto);
  }
}
