/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.perstistence;

import ch.nolix.systemapi.middata.adapter.DataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdata.model.IMultiBackReference;

/**
 * @author Silvan Wyss
 */
public interface IMultiBackReferenceSaver {
  /**
   * Saves the changes of the given multiBackReference.
   * 
   * @param multiBackReference
   * @param dataAndSchemaAdapter
   */
  void saveMultiBackReferenceChanges(
    IMultiBackReference<?> multiBackReference,
    DataAdapterAndSchemaReader dataAndSchemaAdapter);
}
