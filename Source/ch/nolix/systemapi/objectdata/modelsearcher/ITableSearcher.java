/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.modelsearcher;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.systemapi.objectdata.model.IColumn;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.ITable;

/**
 * @author Silvan Wyss
 */
public interface ITableSearcher {
  /**
   * @param table
   * @return the ids of the locally deleted {@link IEntity}s of the given table.
   */
  ExtendedIterable<String> getLocallyDeletedEntityIds(final ITable<?> table);

  /**
   * @param <E>
   * @param table
   * @return the {@link IColumn}s that references the given table.
   */
  <E extends IEntity> ExtendedIterable<IColumn> getStoredColumsThatReferencesTable(ITable<E> table);
}
