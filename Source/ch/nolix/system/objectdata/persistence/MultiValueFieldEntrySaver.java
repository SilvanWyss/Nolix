/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectdata.persistence;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdata.middatamodelmapper.MultiValueEntryDtoMapper;
import ch.nolix.systemapi.middata.adapter.IDataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdata.middatamodelmapper.IMultiValueEntryDtoMapper;
import ch.nolix.systemapi.objectdata.model.IMultiValueFieldEntry;
import ch.nolix.systemapi.objectdata.perstistence.IMultiValueFieldEntrySaver;

/**
 * @author Silvan Wyss
 */
public final class MultiValueFieldEntrySaver implements IMultiValueFieldEntrySaver {
  private static final IMultiValueEntryDtoMapper MULTI_VALUE_ENTRY_DTO_MAPPER = new MultiValueEntryDtoMapper();

  /**
   * {@inheritDoc}
   */
  @Override
  public void saveMultiValueFieldEntryChange(
    final IMultiValueFieldEntry<?> multiValueFieldEntry,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {
    final var multiValueEntryState = multiValueFieldEntry.getState();

    switch (multiValueEntryState) {
      case NEW:
        saveMultiValueFieldEntryCreation(multiValueFieldEntry, dataAndSchemaAdapter);
        break;
      case DELETED:
        saveMultiValueFieldEntryDeletion(multiValueFieldEntry, dataAndSchemaAdapter);
        break;
      default:
        throw //
        InvalidArgumentException.forArgumentAndArgumentName(multiValueEntryState, "state of multi value field entry");
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void saveMultiValueFieldEntryCreation(
    final IMultiValueFieldEntry<?> multiValueFieldEntry,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {
    final var multiValueEntryDto = //
    MULTI_VALUE_ENTRY_DTO_MAPPER.mapMultiValueEntryToMultiValueEntryDto(multiValueFieldEntry);

    dataAndSchemaAdapter.insertMultiValueEntry(multiValueEntryDto);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void saveMultiValueFieldEntryDeletion(
    final IMultiValueFieldEntry<?> multiValueFieldEntry,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {
    final var multiValueEntryDto = //
    MULTI_VALUE_ENTRY_DTO_MAPPER.mapMultiValueEntryToMultiValueEntryDto(multiValueFieldEntry);

    dataAndSchemaAdapter.deleteMultiValueEntry(multiValueEntryDto);
  }
}
