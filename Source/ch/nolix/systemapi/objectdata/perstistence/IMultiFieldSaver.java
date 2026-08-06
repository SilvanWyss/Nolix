/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.perstistence;

import ch.nolix.systemapi.middata.adapter.DataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.Field;

/**
 * @author Silvan Wyss
 */
public interface IMultiFieldSaver {
  /**
   * Saves the changes of the given field if the given field is a multi field.
   * 
   * @param field
   * @param dataAndSchemaAdapter
   */
  void saveFieldChangesIfIsMultiField(Field field, DataAdapterAndSchemaReader dataAndSchemaAdapter);

  /**
   * Saves the changes of the multi fields of the given entity.
   * 
   * @param entity
   * @param dataAndSchemaAdapter
   */
  void saveMultiFieldChangesOfEntity(IEntity entity, DataAdapterAndSchemaReader dataAndSchemaAdapter);
}
