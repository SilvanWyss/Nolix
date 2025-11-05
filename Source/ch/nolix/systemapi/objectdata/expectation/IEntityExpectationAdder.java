package ch.nolix.systemapi.objectdata.expectation;

import ch.nolix.systemapi.middata.adapter.IDataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdata.model.IEntity;

/**
 * @author Silvan Wyss
 * @version 2025-11-05
 */
public interface IEntityExpectationAdder {
  /**
   * Adds expectation to the given entity that its newly referenced entities
   * actually exist.
   * 
   * @param entity
   * @param dataAndSchemaAdapter
   */
  void addExpectationThatNewlyReferencedEntitiesExist(IEntity entity, IDataAdapterAndSchemaReader dataAndSchemaAdapter);

  /**
   * Adds expectation to the given entity that its newly referenced entities
   * actually exist if the given entity is new or edited.
   * 
   * @param entity
   * @param dataAndSchemaAdapter
   */
  void addExpectationThatNewlyReferencedEntitiesExistIfEntityIsNewOrEdited(
    IEntity entity,
    IDataAdapterAndSchemaReader dataAndSchemaAdapter);
}
