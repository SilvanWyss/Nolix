package ch.nolix.systemapi.databaseobject.request;

/**
 * A {@link DatabaseConnectionRequestable} can be asked if it is connected with
 * a real database.
 * 
 * @author Silvan Wyss
 * @version 2024-12-21
 */
public interface DatabaseConnectionRequestable {

  /**
   * @return true if the current {@link DatabaseConnectionRequestable} is
   *         connected with a real database, false otherwise.
   */
  boolean isConnectedWithRealDatabase();
}
