package ch.nolix.systemapi.objectdata.perstistence;

import ch.nolix.systemapi.middata.adapter.IDataAdapterAndSchemaReader;
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
    IDataAdapterAndSchemaReader dataAndSchemaAdapter);
}
