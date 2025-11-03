package ch.nolix.system.objectdata.changesetsaver;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdata.middatamodelmapper.MultiBackReferenceEntryDeletionDtoMapper;
import ch.nolix.system.objectdata.middatamodelmapper.MultiBackReferenceEntryDtoMapper;
import ch.nolix.systemapi.middata.adapter.IDataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdata.changesetsaver.IMultiBackReferenceEntrySaver;
import ch.nolix.systemapi.objectdata.middatamodelmapper.IMultiBackReferenceEntryDeletionDtoMapper;
import ch.nolix.systemapi.objectdata.middatamodelmapper.IMultiBackReferenceEntryDtoMapper;
import ch.nolix.systemapi.objectdata.model.IMultiBackReferenceEntry;

/**
 * @author Silvan Wyss
 * @version 2025-11-03
 */
public final class MultiBackReferenceEntrySaver implements IMultiBackReferenceEntrySaver {
  private static final IMultiBackReferenceEntryDtoMapper MULTI_BACK_REFERENCE_ENTRY_DTO_MAPPER = //
  new MultiBackReferenceEntryDtoMapper();

  private static final IMultiBackReferenceEntryDeletionDtoMapper MULTI_BACK_REFERENCE_ENTRY_DELETION_DTO_MAPPER = //
  new MultiBackReferenceEntryDeletionDtoMapper();

  /**
   * {@inheritDoc}
   */
  @Override
  public void saveMultiBackReferenceEntryChange(
    final IMultiBackReferenceEntry<?> multiBackReferenceEntry,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {
    final var multiBackReferenceEntryState = multiBackReferenceEntry.getState();

    switch (multiBackReferenceEntryState) {
      case NEW:
        saveMultiBackReferenceEntryCreation(multiBackReferenceEntry, dataAndSchemaAdapter);
        break;
      case DELETED:
        saveMultiBackReferenceEntryDeletion(multiBackReferenceEntry, dataAndSchemaAdapter);
        break;
      default:
        throw //
        InvalidArgumentException.forArgumentAndArgumentName(multiBackReferenceEntryState,
          "state of multi back  reference entry");
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void saveMultiBackReferenceEntryCreation(
    final IMultiBackReferenceEntry<?> multiBackReferenceEntry,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {
    final var multiBackReferenceEntryDto = //
    MULTI_BACK_REFERENCE_ENTRY_DTO_MAPPER.mapMultiBackReferenceEntryToMultiBackReferenceEntryDto(
      multiBackReferenceEntry);

    dataAndSchemaAdapter.insertMultiBackReferenceEntry(multiBackReferenceEntryDto);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void saveMultiBackReferenceEntryDeletion(
    final IMultiBackReferenceEntry<?> multiBackReferenceEntry,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {
    final var multiBackReferenceEntryDeletionDto = //
    MULTI_BACK_REFERENCE_ENTRY_DELETION_DTO_MAPPER.mapMultiBackReferenceEntryToMultiBackReferenceEntryDeletionDto(
      multiBackReferenceEntry);

    dataAndSchemaAdapter.deleteMultiBackReferenceEntry(multiBackReferenceEntryDeletionDto);
  }
}
