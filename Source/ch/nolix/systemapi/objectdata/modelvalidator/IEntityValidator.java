/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.modelvalidator;

import ch.nolix.systemapi.objectdata.model.Entity;
import ch.nolix.systemapi.objectdata.model.ITable;

/**
 * @author Silvan Wyss
 */
public interface IEntityValidator {
  /**
   * @param entity
   * @throws RuntimeException if the given entity does not belong to a
   *                          {@link ITable}
   */
  void assertBelongsToTable(Entity entity);

  /**
   * @param entity
   * @throws RuntimeException if the given entity cannot be deleted
   */
  void assertCanBeDeleted(Entity entity);

  /**
   * @param entity
   * @param table
   * @throws RuntimeException if the given table cannot be set as parent
   *                          {@link ITable} to the given entity
   */
  void assertCanSetParentTable(Entity entity, ITable<? extends Entity> table);

  /**
   * @param entity
   * @throws RuntimeException if the given entity belongs to a {@link ITable}
   */
  void assertDoesNotBelongToTable(Entity entity);

  /**
   * @param entity
   * @throws RuntimeException if the given entity does not have a save stamp
   */
  void assertHasSaveStamp(Entity entity);

  /**
   * @param entity
   * @throws RuntimeException if the given entity is referenced, ignoring the
   *                          locally deleted {@link Entity}s
   */
  void assertIsNotReferencedIgnoringLocallyDeletedEntities(Entity entity);
}
