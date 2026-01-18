/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.entitytool;

import ch.nolix.systemapi.objectdata.model.IEntity;

/**
 * @author Silvan Wyss
 */
public interface ITableNameExtractor {
  String getTableNameOfEntity(IEntity entity);

  String getTableNameOfEntityType(Class<? extends IEntity> entityType);
}
