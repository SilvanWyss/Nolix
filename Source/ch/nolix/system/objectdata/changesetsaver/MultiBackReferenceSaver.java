package ch.nolix.system.objectdata.changesetsaver;

import ch.nolix.systemapi.middata.adapter.IDataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdata.changesetsaver.IMultiBackReferenceEntrySaver;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IMultiBackReference;

public final class MultiBackReferenceSaver {
  private static final IMultiBackReferenceEntrySaver MULTI_BACK_REFERENCE_ENTRY_SAVER = //
  new MultiBackReferenceEntrySaver();

  public void saveMultiBackReference(
    final IMultiBackReference<? extends IEntity> multiBackReference,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {
    for (final var e : multiBackReference.getStoredNewAndDeletedEntries()) {
      MULTI_BACK_REFERENCE_ENTRY_SAVER.saveMultiBackReferenceEntryChange(e, dataAndSchemaAdapter);
    }
  }
}
