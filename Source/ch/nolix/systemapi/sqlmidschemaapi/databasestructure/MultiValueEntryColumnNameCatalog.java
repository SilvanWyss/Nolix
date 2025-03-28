package ch.nolix.systemapi.sqlmidschemaapi.databasestructure;

/**
 * Of the {@link MultiValueEntryColumnNameCatalog} an instance cannot be
 * created.
 * 
 * @author Silvan Wyss
 * @version 2025-03-28
 */
public final class MultiValueEntryColumnNameCatalog {

  public static final String ENTITY_ID = "EntityId";

  public static final String MULTI_VALUE_COLUMN_ID = "MultiValueColumnId";

  //'Value' is a reserved word in MSSQL databases.
  public static final String VALUE = "Value_";

  /**
   * Prevents that an instance of the {@link MultiValueEntryColumnNameCatalog} can
   * be created.
   */
  private MultiValueEntryColumnNameCatalog() {
  }
}
