/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectdata.persistence;

import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdata.middatamodelmapper.MultiBackReferenceEntryDeletionDtoMapper;
import ch.nolix.system.objectdata.middatamodelmapper.MultiBackReferenceEntryDtoMapper;
import ch.nolix.systemapi.middata.adapter.DataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdata.model.IMultiBackReferenceEntry;
import ch.nolix.systemapi.objectdata.perstistence.IMultiBackReferenceEntrySaver;

/**
 * @author Silvan Wyss
 */
public final class MultiBackReferenceEntrySaver implements IMultiBackReferenceEntrySaver {
  private static final MultiBackReferenceEntryDtoMapper MULTI_BACK_REFERENCE_ENTRY_DTO_MAPPER = //
  new MultiBackReferenceEntryDtoMapper();

  private static final MultiBackReferenceEntryDeletionDtoMapper MULTI_BACK_REFERENCE_ENTRY_DELETION_DTO_MAPPER = //
  new MultiBackReferenceEntryDeletionDtoMapper();

  /**
   * {@inheritDoc}
   */
  @Override
  public void saveMultiBackReferenceEntryChange(
    final IMultiBackReferenceEntry<?> multiBackReferenceEntry,
    final DataAdapterAndSchemaReader dataAndSchemaAdapter) {
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
    final DataAdapterAndSchemaReader dataAndSchemaAdapter) {
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
    final DataAdapterAndSchemaReader dataAndSchemaAdapter) {
    final var multiBackReferenceEntryDeletionDto = //
    MULTI_BACK_REFERENCE_ENTRY_DELETION_DTO_MAPPER.mapMultiBackReferenceEntryToMultiBackReferenceEntryDeletionDto(
      multiBackReferenceEntry);

    dataAndSchemaAdapter.deleteMultiBackReferenceEntry(multiBackReferenceEntryDeletionDto);
  }
}
