/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.objectcomposition.datamodelcomponent;

/**
 * A {@link DatabaseComponent} can belong to a database.
 * 
 * @author Silvan Wyss
 * @param <D> the type of the database a {@link DatabaseComponent} can belong
 *            to.
 */
public interface DatabaseComponent<D> {
  /**
   * @return true if the current {@link DatabaseComponent} belongs to a database,
   *         false otherwise
   */
  boolean belongsToDatabase();

  /**
   * @return the database of the current {@link DatabaseComponent}
   * @throws RuntimeException if the current {@link DatabaseComponent} does not
   *                          belong to a database
   */
  D getStoredParentDatabase();
}
