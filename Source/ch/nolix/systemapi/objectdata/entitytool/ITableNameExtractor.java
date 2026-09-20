/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.entitytool;

import ch.nolix.systemapi.objectdata.model.Entity;

/**
 * @author Silvan Wyss
 */
public interface ITableNameExtractor {
  String getTableNameOfEntity(Entity entity);

  String getTableNameOfEntityType(Class<? extends Entity> entityType);
}
