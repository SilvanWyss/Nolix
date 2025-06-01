package ch.nolix.systemapi.midschemaapi.databasestructureapi;

/**
 * Of the {@link DatabasePropertyNameCatalog} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @version 2025-06-01
 */
public final class DatabasePropertyNameCatalog {

  public static final String SCHEMA_TIMESTAMP = "SchemaTimestamp";

  /**
   * Prevents that an instance of the {@link DatabasePropertyNameCatalog} can be
   * created.
   */
  private DatabasePropertyNameCatalog() {
  }
}
