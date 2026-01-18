/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.perstistence;

import ch.nolix.systemapi.middata.adapter.IDataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdata.model.IMultiReference;

/**
 * @author Silvan Wyss
 */
public interface IMultiReferenceSaver {
  /**
   * Saves the changes of the given multiReference.
   * 
   * @param multiReference
   * @param dataAndSchemaAdapter
   */
  void saveMultiReferenceChanges(IMultiReference<?> multiReference, IDataAdapterAndSchemaReader dataAndSchemaAdapter);
}
