package ch.nolix.systemapi.objectdata.perstistence;

import ch.nolix.systemapi.middata.adapter.IDataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdata.model.IMultiValueFieldEntry;

/**
 * @author Silvan Wyss
 * @version 2025-11-02
 */
public interface IMultiValueFieldEntrySaver {
  /**
   * Saves the change of the given multiValueFieldEntry.
   * 
   * @param multiValueFieldEntry
   * @param dataAndSchemaAdapter
   */
  void saveMultiValueFieldEntryChange(
    IMultiValueFieldEntry<?> multiValueFieldEntry,
    IDataAdapterAndSchemaReader dataAndSchemaAdapter);

  /**
   * Saves the creation of the given multiValueFieldEntry.
   * 
   * @param multiValueFieldEntry
   * @param dataAndSchemaAdapter
   */
  void saveMultiValueFieldEntryCreation(
    IMultiValueFieldEntry<?> multiValueFieldEntry,
    IDataAdapterAndSchemaReader dataAndSchemaAdapter);

  /**
   * Saves the deletion of the given multiValueFieldEntry.
   * 
   * @param multiValueFieldEntry
   * @param dataAndSchemaAdapter
   */
  void saveMultiValueFieldEntryDeletion(
    IMultiValueFieldEntry<?> multiValueFieldEntry,
    IDataAdapterAndSchemaReader dataAndSchemaAdapter);
}
