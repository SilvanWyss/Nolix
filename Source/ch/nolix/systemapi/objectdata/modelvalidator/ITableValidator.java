/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.modelvalidator;

import ch.nolix.systemapi.objectdata.model.Entity;
import ch.nolix.systemapi.objectdata.model.ITable;

/**
 * @author Silvan Wyss
 */
public interface ITableValidator {
  /**
   * @param table
   * @param entity
   * @throws RuntimeException if the given table cannot insert the given entity
   */
  void assertCanInsertEntity(ITable<? extends Entity> table, Entity entity);
}
