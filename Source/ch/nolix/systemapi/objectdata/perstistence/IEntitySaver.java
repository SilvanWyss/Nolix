/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.perstistence;

import ch.nolix.systemapi.middata.adapter.IDataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdata.model.IEntity;

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
  void saveEntityChanges(IEntity entity, IDataAdapterAndSchemaReader dataAndSchemaAdapter);

  /**
   * Saves the creation of the given entity.
   * 
   * @param entity
   * @param dataAndSchemaAdapter
   */
  void saveEntityCreation(IEntity entity, IDataAdapterAndSchemaReader dataAndSchemaAdapter);

  /**
   * Saves the deletion of the given entity.
   * 
   * @param entity
   * @param dataAndSchemaAdapter
   */
  void saveEntityDeletion(IEntity entity, IDataAdapterAndSchemaReader dataAndSchemaAdapter);

  /**
   * Saves the updates of the given entity.
   * 
   * @param entity
   * @param dataAndSchemaAdapter
   */
  void saveEntityUpdates(IEntity entity, IDataAdapterAndSchemaReader dataAndSchemaAdapter);
}
