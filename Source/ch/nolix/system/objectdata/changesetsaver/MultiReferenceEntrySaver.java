package ch.nolix.system.objectdata.changesetsaver;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdata.middatamodelmapper.MultiReferenceEntryDeletionDtoMapper;
import ch.nolix.system.objectdata.middatamodelmapper.MultiReferenceEntryDtoMapper;
import ch.nolix.systemapi.middata.adapter.IDataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdata.changesetsaver.IMultiReferenceEntrySaver;
import ch.nolix.systemapi.objectdata.middatamodelmapper.IMultiReferenceEntryDeletionDtoMapper;
import ch.nolix.systemapi.objectdata.middatamodelmapper.IMultiReferenceEntryDtoMapper;
import ch.nolix.systemapi.objectdata.model.IMultiReferenceEntry;

/**
 * @author Silvan Wyss
 * @version 2025-11-02
 */
public final class MultiReferenceEntrySaver implements IMultiReferenceEntrySaver {
  private static final IMultiReferenceEntryDtoMapper MULTI_REFERENCE_ENTRY_DTO_MAPPER = //
  new MultiReferenceEntryDtoMapper();

  private static final IMultiReferenceEntryDeletionDtoMapper MULTI_REFERENCE_ENTRY_DELETION_DTO_MAPPER = //
  new MultiReferenceEntryDeletionDtoMapper();

  /**
   * {@inheritDoc}
   */
  @Override
  public void saveMultiReferenceEntryChange(
    final IMultiReferenceEntry<?> multiReferenceEntry,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {
    final var state = multiReferenceEntry.getState();

    switch (state) {
      case NEW:
        saveMultiReferenceEntryCreation(multiReferenceEntry, dataAndSchemaAdapter);
        break;
      case UNEDITED:
        break;
      case DELETED:
        saveMultiReferenceEntryDeletion(multiReferenceEntry, dataAndSchemaAdapter);
        break;
      default:
        throw InvalidArgumentException.forArgumentAndArgumentName(state, "state of multi reference entry");
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void saveMultiReferenceEntryCreation(
    final IMultiReferenceEntry<?> multiReferenceEntry,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {
    final var multiReferenceEntryDto = //
    MULTI_REFERENCE_ENTRY_DTO_MAPPER.mapMultiReferenceEntryToMultiReferenceEntryDto(multiReferenceEntry);

    dataAndSchemaAdapter.insertMultiReferenceEntry(multiReferenceEntryDto);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void saveMultiReferenceEntryDeletion(
    final IMultiReferenceEntry<?> multiReferenceEntry,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {
    final var multiReferenceEntryDeletionDto = //
    MULTI_REFERENCE_ENTRY_DELETION_DTO_MAPPER.mapMultiReferenceEntryToMultiReferenceEntryDeletionDto(
      multiReferenceEntry);

    dataAndSchemaAdapter.deleteMultiReferenceEntry(multiReferenceEntryDeletionDto);
  }
}
