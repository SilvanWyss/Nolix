/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectdata.modelexaminer;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.objectdata.model.IDatabase;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.ITable;
import ch.nolix.systemapi.objectdata.modelexaminer.IEntityExaminerHelper;

/**
 * @author Silvan Wyss
 */
public final class EntityExaminerHelper implements IEntityExaminerHelper {
  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<String> getLocallyDeletedEntitiesIds(final IDatabase database) {
    return //
    database
      .getStoredTables()
      .toMultiples(ITable::internalGetStoredEntitiesInLocalData)
      .getViewOfStoredSelected(IEntity::isDeleted)
      .to(IEntity::getId);
  }
}
