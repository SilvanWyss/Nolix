/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.perstistence;

import ch.nolix.systemapi.middata.adapter.DataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdata.model.IMultiValueFieldEntry;

/**
 * @author Silvan Wyss
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
    DataAdapterAndSchemaReader dataAndSchemaAdapter);

  /**
   * Saves the creation of the given multiValueFieldEntry.
   * 
   * @param multiValueFieldEntry
   * @param dataAndSchemaAdapter
   */
  void saveMultiValueFieldEntryCreation(
    IMultiValueFieldEntry<?> multiValueFieldEntry,
    DataAdapterAndSchemaReader dataAndSchemaAdapter);

  /**
   * Saves the deletion of the given multiValueFieldEntry.
   * 
   * @param multiValueFieldEntry
   * @param dataAndSchemaAdapter
   */
  void saveMultiValueFieldEntryDeletion(
    IMultiValueFieldEntry<?> multiValueFieldEntry,
    DataAdapterAndSchemaReader dataAndSchemaAdapter);
}
