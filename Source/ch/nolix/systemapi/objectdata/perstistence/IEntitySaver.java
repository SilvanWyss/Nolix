/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.perstistence;

import ch.nolix.systemapi.middata.adapter.DataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdata.model.Entity;

/**
 * @author Silvan Wyss
 */
public interface IEntitySaver {
  /**
   * Saves the changes of the given entity.
   * 
   * @param entity
   * @param dataAndSchemaAdapter
   */
  void saveEntityChanges(Entity entity, DataAdapterAndSchemaReader dataAndSchemaAdapter);

  /**
   * Saves the creation of the given entity.
   * 
   * @param entity
   * @param dataAndSchemaAdapter
   */
  void saveEntityCreation(Entity entity, DataAdapterAndSchemaReader dataAndSchemaAdapter);

  /**
   * Saves the deletion of the given entity.
   * 
   * @param entity
   * @param dataAndSchemaAdapter
   */
  void saveEntityDeletion(Entity entity, DataAdapterAndSchemaReader dataAndSchemaAdapter);

  /**
   * Saves the updates of the given entity.
   * 
   * @param entity
   * @param dataAndSchemaAdapter
   */
  void saveEntityUpdates(Entity entity, DataAdapterAndSchemaReader dataAndSchemaAdapter);
}
