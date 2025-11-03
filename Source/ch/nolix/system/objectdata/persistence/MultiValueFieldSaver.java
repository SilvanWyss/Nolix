package ch.nolix.system.objectdata.persistence;

import ch.nolix.systemapi.middata.adapter.IDataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdata.model.IMultiValueField;
import ch.nolix.systemapi.objectdata.perstistence.IMultiValueFieldEntrySaver;
import ch.nolix.systemapi.objectdata.perstistence.IMultiValueFieldSaver;

/**
 * @author Silvan Wyss
 * @version 2025-11-02
 */
public final class MultiValueFieldSaver implements IMultiValueFieldSaver {
  private static final IMultiValueFieldEntrySaver MULTI_VALUE_FIELD_ENTRY_SAVER = new MultiValueFieldEntrySaver();

  /**
   * {@inheritDoc}
   */
  @Override
  public void saveMultiValueFieldChanges(
    final IMultiValueField<?> multiValueField,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {
    for (final var e : multiValueField.getStoredNewAndDeletedEntries()) {
      MULTI_VALUE_FIELD_ENTRY_SAVER.saveMultiValueFieldEntryChange(e, dataAndSchemaAdapter);
    }
  }
}
