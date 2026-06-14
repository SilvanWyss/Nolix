/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.modelsearcher;

import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;
import ch.nolix.systemapi.databaseobject.modelexaminer.IDatabaseObjectExaminer;
import ch.nolix.systemapi.objectdata.model.IColumn;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.ITable;

/**
 * @author Silvan Wyss
 */
public interface ITableSearcher extends IDatabaseObjectExaminer {
  /**
   * @param table
   * @return the ids of the locally deleted {@link IEntity}s of the given table.
   */
  IWellOrderContainer<String> getLocallyDeletedEntityIds(final ITable<?> table);

  /**
   * @param <E>
   * @param table
   * @return the {@link IColumn}s that references the given table.
   */
  <E extends IEntity> IWellOrderContainer<IColumn> getStoredColumsThatReferencesTable(ITable<E> table);
}
