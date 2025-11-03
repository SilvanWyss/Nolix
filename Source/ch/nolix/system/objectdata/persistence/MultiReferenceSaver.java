package ch.nolix.system.objectdata.persistence;

import ch.nolix.systemapi.middata.adapter.IDataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdata.model.IMultiReference;
import ch.nolix.systemapi.objectdata.perstistence.IMultiReferenceEntrySaver;
import ch.nolix.systemapi.objectdata.perstistence.IMultiReferenceSaver;

/**
 * @author Silvan Wyss
 * @version 2023-01-07
 */
public final class MultiReferenceSaver implements IMultiReferenceSaver {
  private static final IMultiReferenceEntrySaver MULTI_REFERENCE_ENTRY_SAVER = new MultiReferenceEntrySaver();

  /**
   * {@inheritDoc}
   */
  @Override
  public void saveMultiReferenceChanges(
    final IMultiReference<?> multiReference,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {
    for (final var e : multiReference.getStoredNewAndDeletedEntries()) {
      MULTI_REFERENCE_ENTRY_SAVER.saveMultiReferenceEntryChange(e, dataAndSchemaAdapter);
    }
  }
}
