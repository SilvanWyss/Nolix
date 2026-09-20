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
  void assertBelongsToTable(Entity entity);

  void assertCanBeDeleted(Entity entity);

  void assertCanSetParentTable(Entity entity, ITable<? extends Entity> table);

  void assertDoesNotBelongToTable(Entity entity);

  void assertHasSaveStamp(Entity entity);

  void assertIsNotReferencedIgnoringLocallyDeletedEntities(Entity entity);
}
