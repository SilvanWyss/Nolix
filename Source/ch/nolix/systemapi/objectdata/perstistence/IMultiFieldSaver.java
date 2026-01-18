/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.perstistence;

import ch.nolix.systemapi.middata.adapter.IDataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IField;

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
  void saveFieldChangesIfIsMultiField(IField field, IDataAdapterAndSchemaReader dataAndSchemaAdapter);

  /**
   * Saves the changes of the multi fields of the given entity.
   * 
   * @param entity
   * @param dataAndSchemaAdapter
   */
  void saveMultiFieldChangesOfEntity(IEntity entity, IDataAdapterAndSchemaReader dataAndSchemaAdapter);
}
