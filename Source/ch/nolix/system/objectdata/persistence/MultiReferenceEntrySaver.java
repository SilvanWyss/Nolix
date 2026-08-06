/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectdata.persistence;

import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdata.middatamodelmapper.MultiReferenceEntryDeletionDtoMapper;
import ch.nolix.system.objectdata.middatamodelmapper.MultiReferenceEntryDtoMapper;
import ch.nolix.systemapi.middata.adapter.DataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdata.model.IMultiReferenceEntry;
import ch.nolix.systemapi.objectdata.perstistence.IMultiReferenceEntrySaver;

/**
 * @author Silvan Wyss
 */
public final class MultiReferenceEntrySaver implements IMultiReferenceEntrySaver {
  private static final MultiReferenceEntryDtoMapper MULTI_REFERENCE_ENTRY_DTO_MAPPER = //
  new MultiReferenceEntryDtoMapper();

  private static final MultiReferenceEntryDeletionDtoMapper MULTI_REFERENCE_ENTRY_DELETION_DTO_MAPPER = //
  new MultiReferenceEntryDeletionDtoMapper();

  /**
   * {@inheritDoc}
   */
  @Override
  public void saveMultiReferenceEntryChange(
    final IMultiReferenceEntry<?> multiReferenceEntry,
    final DataAdapterAndSchemaReader dataAndSchemaAdapter) {
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
    final DataAdapterAndSchemaReader dataAndSchemaAdapter) {
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
    final DataAdapterAndSchemaReader dataAndSchemaAdapter) {
    final var multiReferenceEntryDeletionDto = //
    MULTI_REFERENCE_ENTRY_DELETION_DTO_MAPPER.mapMultiReferenceEntryToMultiReferenceEntryDeletionDto(
      multiReferenceEntry);

    dataAndSchemaAdapter.deleteMultiReferenceEntry(multiReferenceEntryDeletionDto);
  }
}
