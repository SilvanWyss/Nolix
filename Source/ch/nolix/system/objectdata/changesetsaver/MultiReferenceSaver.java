package ch.nolix.system.objectdata.changesetsaver;

import ch.nolix.systemapi.middata.adapter.IDataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdata.changesetsaver.IMultiReferenceEntrySaver;
import ch.nolix.systemapi.objectdata.model.IMultiReference;

public final class MultiReferenceSaver {
  private static final IMultiReferenceEntrySaver MULTI_REFERENCE_ENTRY_SAVER = new MultiReferenceEntrySaver();

  public void saveMultiReference(
    final IMultiReference<?> multiReference,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {
    for (final var e : multiReference.getStoredNewAndDeletedEntries()) {
      MULTI_REFERENCE_ENTRY_SAVER.saveMultiReferenceEntryChange(e, dataAndSchemaAdapter);
    }
  }
}
