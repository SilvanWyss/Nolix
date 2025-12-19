package ch.nolix.systemapi.midschema.databasestructure;

/**
 * Of the {@link DatabasePropertyNameCatalog} an instance cannot be created.
 * 
 * @author Silvan Wyss
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
