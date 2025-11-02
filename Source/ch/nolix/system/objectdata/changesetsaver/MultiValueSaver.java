package ch.nolix.system.objectdata.changesetsaver;

import ch.nolix.systemapi.middata.adapter.IDataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdata.changesetsaver.IMultiValueFieldEntrySaver;
import ch.nolix.systemapi.objectdata.model.IMultiValueField;

public final class MultiValueSaver {
  private static final IMultiValueFieldEntrySaver MULTI_VALUE_FIELD_ENTRY_SAVER = new MultiValueFieldEntrySaver();

  public void saveChangesOfMultiValue(
    final IMultiValueField<?> multiValue,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {
    for (final var e : multiValue.getStoredNewAndDeletedEntries()) {
      MULTI_VALUE_FIELD_ENTRY_SAVER.saveMultiValueFieldEntryChange(e, dataAndSchemaAdapter);
    }
  }
}
