/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.entitytool;

import ch.nolix.systemapi.objectdata.model.Entity;

/**
 * @author Silvan Wyss
 */
public interface ITableNameExtractor {
  /**
   * @param entity
   * @return the name of the ITable of the given entity
   */
  String getTableNameOfEntity(Entity entity);

  /**
   * @param entityType
   * @return the name of the ITable for the given entityType
   */
  String getTableNameOfEntityType(Class<? extends Entity> entityType);
}
