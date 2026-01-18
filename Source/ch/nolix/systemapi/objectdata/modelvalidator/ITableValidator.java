/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.modelvalidator;

import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.ITable;

/**
 * @author Silvan Wyss
 */
public interface ITableValidator {
  void assertCanInsertEntity(ITable<? extends IEntity> table, IEntity entity);
}
