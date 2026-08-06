/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.perstistence;

import ch.nolix.systemapi.middata.adapter.DataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdata.model.IMultiReferenceEntry;

/**
 * @author Silvan Wyss
 */
public interface IMultiReferenceEntrySaver {
  /**
   * Saves the change of the given multiReferenceEntry.
   * 
   * @param multiReferenceEntry
   * @param dataAndSchemaAdapter
   */
  void saveMultiReferenceEntryChange(
    IMultiReferenceEntry<?> multiReferenceEntry,
    DataAdapterAndSchemaReader dataAndSchemaAdapter);

  /**
   * Saves the creation of the given multiReferenceEntry.
   * 
   * @param multiReferenceEntry
   * @param dataAndSchemaAdapter
   */
  void saveMultiReferenceEntryCreation(
    IMultiReferenceEntry<?> multiReferenceEntry,
    DataAdapterAndSchemaReader dataAndSchemaAdapter);

  /**
   * Saves the deletion of the given multiReferenceEntry.
   * 
   * @param multiReferenceEntry
   * @param dataAndSchemaAdapter
   */
  void saveMultiReferenceEntryDeletion(
    IMultiReferenceEntry<?> multiReferenceEntry,
    DataAdapterAndSchemaReader dataAndSchemaAdapter);
}
