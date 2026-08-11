/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.objectcomposition.databasemanager;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;

/**
 * @author Silvan Wyss
 * @param <T> the type of the tables of a {@link TableManager}
 */
public interface TableManager<T> {
  /**
   * @return the tables of the current {@link TableManager}
   */
  ExtendedIterable<? extends T> getStoredTables();
}
