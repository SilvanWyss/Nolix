package ch.nolix.systemapi.objectdata.changesetsaver;

import ch.nolix.systemapi.middata.adapter.IDataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdata.model.IMultiBackReference;

/**
 * @author Silvan Wyss
 * @version 2025-11-03
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
