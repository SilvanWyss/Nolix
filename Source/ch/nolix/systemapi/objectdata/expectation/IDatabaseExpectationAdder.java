package ch.nolix.systemapi.objectdata.expectation;

import ch.nolix.systemapi.middata.adapter.IDataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdata.model.IDatabase;

/**
 * @author Silvan Wyss
 * @version 2025-11-05
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
