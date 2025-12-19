package ch.nolix.system.objectdata.persistence;

import ch.nolix.systemapi.middata.adapter.IDataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IMultiBackReference;
import ch.nolix.systemapi.objectdata.perstistence.IMultiBackReferenceEntrySaver;
import ch.nolix.systemapi.objectdata.perstistence.IMultiBackReferenceSaver;

/**
 * @author Silvan Wyss
 */
public final class MultiBackReferenceSaver implements IMultiBackReferenceSaver {
  private static final IMultiBackReferenceEntrySaver MULTI_BACK_REFERENCE_ENTRY_SAVER = //
  new MultiBackReferenceEntrySaver();

  /**
   * {@inheritDoc}
   */
  @Override
  public void saveMultiBackReferenceChanges(
    final IMultiBackReference<? extends IEntity> multiBackReference,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {
    for (final var e : multiBackReference.getStoredNewAndDeletedEntries()) {
      MULTI_BACK_REFERENCE_ENTRY_SAVER.saveMultiBackReferenceEntryChange(e, dataAndSchemaAdapter);
    }
  }
}
