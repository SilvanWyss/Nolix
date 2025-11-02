package ch.nolix.system.objectdata.changesetsaver;

import ch.nolix.systemapi.middata.adapter.IDataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdata.changesetsaver.IMultiValueFieldEntrySaver;
import ch.nolix.systemapi.objectdata.changesetsaver.IMultiValueFieldSaver;
import ch.nolix.systemapi.objectdata.model.IMultiValueField;

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
  public void saveChangesOfMultiValueField(
    final IMultiValueField<?> multiValueField,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {
    for (final var e : multiValueField.getStoredNewAndDeletedEntries()) {
      MULTI_VALUE_FIELD_ENTRY_SAVER.saveMultiValueFieldEntryChange(e, dataAndSchemaAdapter);
    }
  }
}
