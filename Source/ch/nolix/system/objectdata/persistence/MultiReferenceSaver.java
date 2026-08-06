/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectdata.persistence;

import ch.nolix.systemapi.middata.adapter.DataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdata.model.IMultiReference;
import ch.nolix.systemapi.objectdata.perstistence.IMultiReferenceSaver;

/**
 * @author Silvan Wyss
 */
public final class MultiReferenceSaver implements IMultiReferenceSaver {
  private static final MultiReferenceEntrySaver MULTI_REFERENCE_ENTRY_SAVER = new MultiReferenceEntrySaver();

  /**
   * {@inheritDoc}
   */
  @Override
  public void saveMultiReferenceChanges(
    final IMultiReference<?> multiReference,
    final DataAdapterAndSchemaReader dataAndSchemaAdapter) {
    for (final var e : multiReference.getStoredNewAndDeletedEntries()) {
      MULTI_REFERENCE_ENTRY_SAVER.saveMultiReferenceEntryChange(e, dataAndSchemaAdapter);
    }
  }
}
