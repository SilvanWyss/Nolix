/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.expectation;

import ch.nolix.systemapi.middata.adapter.IDataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdata.model.IDatabase;

/**
 * @author Silvan Wyss
 */
public interface IDatabaseExpectationAdder {
  /**
   * Adds expectation to the given database that its newly referenced entities
   * actually exist.
   * 
   * @param database
   * @param dataAndSchemaAdapter
   */
  void addExpectationThatNewlyReferencedEntitiesExist(
    IDatabase database,
    IDataAdapterAndSchemaReader dataAndSchemaAdapter);
}
