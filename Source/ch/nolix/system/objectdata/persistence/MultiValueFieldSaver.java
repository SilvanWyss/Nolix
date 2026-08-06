/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectdata.persistence;

import ch.nolix.systemapi.middata.adapter.DataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdata.model.IMultiValueField;
import ch.nolix.systemapi.objectdata.perstistence.IMultiValueFieldSaver;

/**
 * @author Silvan Wyss
 */
public final class MultiValueFieldSaver implements IMultiValueFieldSaver {
  private static final MultiValueFieldEntrySaver MULTI_VALUE_FIELD_ENTRY_SAVER = new MultiValueFieldEntrySaver();

  /**
   * {@inheritDoc}
   */
  @Override
  public void saveMultiValueFieldChanges(
    final IMultiValueField<?> multiValueField,
    final DataAdapterAndSchemaReader dataAndSchemaAdapter) {
    for (final var e : multiValueField.getStoredNewAndDeletedEntries()) {
      MULTI_VALUE_FIELD_ENTRY_SAVER.saveMultiValueFieldEntryChange(e, dataAndSchemaAdapter);
    }
  }
}
