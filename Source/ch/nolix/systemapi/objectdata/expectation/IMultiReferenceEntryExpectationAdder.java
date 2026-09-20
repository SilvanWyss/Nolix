/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.expectation;

import ch.nolix.systemapi.middata.adapter.DataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdata.model.Entity;
import ch.nolix.systemapi.objectdata.model.IMultiReferenceEntry;

/**
 * @author Silvan Wyss
 */
public interface IMultiReferenceEntryExpectationAdder {
  /**
   * Adds expectation to the given multiReferenceEntry that its newly referenced
   * entities actually exist.
   * 
   * @param multiReferenceEntry
   * @param dataAndSchemaAdapter
   */
  void addExpectationThatNewlyReferencedEntitiesExist(
    IMultiReferenceEntry<? extends Entity> multiReferenceEntry,
    DataAdapterAndSchemaReader dataAndSchemaAdapter);

  /**
   * Adds expectation to the given multiReferenceEntry that its newly referenced
   * entities actually exist if the given multiReferenceEntry is new.
   * 
   * @param multiReferenceEntry
   * @param dataAndSchemaAdapter
   */
  void addExpectationThatNewlyReferencedEntitiesExistIfMultiReferenceEntryIsNew(
    IMultiReferenceEntry<? extends Entity> multiReferenceEntry,
    DataAdapterAndSchemaReader dataAndSchemaAdapter);
}
