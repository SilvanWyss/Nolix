package ch.nolix.systemapi.objectdata.perstistence;

import ch.nolix.systemapi.middata.adapter.IDataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdata.model.IMultiBackReferenceEntry;

/**
 * @author Silvan Wyss
 * @version 2025-11-03
 */
public interface IMultiBackReferenceEntrySaver {
  /**
   * Saves the change of the given multiBackReferenceEntry.
   * 
   * @param multiBackReferenceEntry
   * @param dataAndSchemaAdapter
   */
  void saveMultiBackReferenceEntryChange(
    IMultiBackReferenceEntry<?> multiBackReferenceEntry,
    IDataAdapterAndSchemaReader dataAndSchemaAdapter);

  /**
   * Saves the creation of the given multiBackReferenceEntry.
   * 
   * @param multiBackReferenceEntry
   * @param dataAndSchemaAdapter
   */
  void saveMultiBackReferenceEntryCreation(
    IMultiBackReferenceEntry<?> multiBackReferenceEntry,
    IDataAdapterAndSchemaReader dataAndSchemaAdapter);

  /**
   * Saves the deletion of the given multiBackReferenceEntry.
   * 
   * @param multiBackReferenceEntry
   * @param dataAndSchemaAdapter
   */
  void saveMultiBackReferenceEntryDeletion(
    IMultiBackReferenceEntry<?> multiBackReferenceEntry,
    IDataAdapterAndSchemaReader dataAndSchemaAdapter);
}
