package ch.nolix.coreapi.attribute.optionalattribute;

/**
 * A {@link IOptionalDatabaseNameHolder} can have a database name.
 * 
 * @author Silvan Wyss
 * @version 2025-06-01
 */
public interface IOptionalDatabaseNameHolder {

  /**
   * @return the database name of the current {@link IOptionalDatabaseNameHolder}.
   * @throws RuntimeException if the current {@link IOptionalDatabaseNameHolder}
   *                          does not have a database name.
   */
  String getDatabaseName();

  /**
   * @return true if the current {@link IOptionalDatabaseNameHolder} has a
   *         database name, false otherwise.
   */
  boolean hasDatabaseName();
}
