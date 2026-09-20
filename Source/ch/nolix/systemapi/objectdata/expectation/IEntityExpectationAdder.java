/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.expectation;

import ch.nolix.systemapi.middata.adapter.DataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdata.model.Entity;

/**
 * @author Silvan Wyss
 */
public interface IEntityExpectationAdder {
  /**
   * Adds expectation to the given entity that its newly referenced entities
   * actually exist.
   * 
   * @param entity
   * @param dataAndSchemaAdapter
   */
  void addExpectationThatNewlyReferencedEntitiesExist(Entity entity, DataAdapterAndSchemaReader dataAndSchemaAdapter);

  /**
   * Adds expectation to the given entity that its newly referenced entities
   * actually exist if the given entity is new or edited.
   * 
   * @param entity
   * @param dataAndSchemaAdapter
   */
  void addExpectationThatNewlyReferencedEntitiesExistIfEntityIsNewOrEdited(
    Entity entity,
    DataAdapterAndSchemaReader dataAndSchemaAdapter);
}
