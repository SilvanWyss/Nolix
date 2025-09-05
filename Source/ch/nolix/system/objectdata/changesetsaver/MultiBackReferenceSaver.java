package ch.nolix.system.objectdata.changesetsaver;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdata.middatamodelmapper.MultiBackReferenceEntryDeletionDtoMapper;
import ch.nolix.system.objectdata.middatamodelmapper.MultiBackReferenceEntryDtoMapper;
import ch.nolix.systemapi.middata.adapter.IDataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdata.middatamodelmapper.IMultiBackReferenceEntryDeletionDtoMapper;
import ch.nolix.systemapi.objectdata.middatamodelmapper.IMultiBackReferenceEntryDtoMapper;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IMultiBackReference;
import ch.nolix.systemapi.objectdata.model.IMultiBackReferenceEntry;

public final class MultiBackReferenceSaver {
  private static final IMultiBackReferenceEntryDtoMapper MULTI_BACK_REFERENCE_ENTRY_DTO_MAPPER = //
  new MultiBackReferenceEntryDtoMapper();

  private static final IMultiBackReferenceEntryDeletionDtoMapper MULTI_BACK_REFERENCE_ENTRY_DELETION_DTO_MAPPER = //
  new MultiBackReferenceEntryDeletionDtoMapper();

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
      case UNEDITED:
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
    final var multiBackReferenceEntryDto = //
    MULTI_BACK_REFERENCE_ENTRY_DTO_MAPPER.mapMultiBackReferenceEntryToMultiBackReferenceEntryDto(
      multiBackReferenceEntry);

    dataAndSchemaAdapter.insertMultiBackReferenceEntry(multiBackReferenceEntryDto);
  }

  private void deleteMultiBackReferenceEntry(
    final IMultiBackReferenceEntry<?> multiBackReferenceEntry,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {
    final var multiBackReferenceEntryDeletionDto = //
    MULTI_BACK_REFERENCE_ENTRY_DELETION_DTO_MAPPER
      .mapMultiBackReferenceEntryToMultiBackReferenceEntryDeletionDto(multiBackReferenceEntry);

    dataAndSchemaAdapter.deleteMultiBackReferenceEntry(multiBackReferenceEntryDeletionDto);
  }
}
