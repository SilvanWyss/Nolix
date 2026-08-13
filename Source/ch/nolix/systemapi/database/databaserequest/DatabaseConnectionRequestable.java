/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.database.databaserequest;

/**
 * @author Silvan Wyss
 */
public interface DatabaseConnectionRequestable {
  /**
   * @return true if the current {@link DatabaseConnectionRequestable} is
   *         connected with a real database, false otherwise
   */
  boolean isConnectedWithRealDatabase();
}
