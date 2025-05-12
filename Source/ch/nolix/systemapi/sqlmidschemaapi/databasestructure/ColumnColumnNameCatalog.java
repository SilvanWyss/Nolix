package ch.nolix.systemapi.sqlmidschemaapi.databasestructure;

/**
 * Of the {@link ColumnColumnNameCatalog} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @version 2025-05-12
 */
public final class ColumnColumnNameCatalog {

  public static final String ID = "Id";

  public static final String PARENT_TABLE_ID = "ParentTableId";

  public static final String NAME = "Name";

  public static final String CONTENT_MODEL_ID = "ContentModelId";

  /**
   * Prevents that an instance of the {@link ColumnColumnNameCatalog} can be
   * created.
   */
  private ColumnColumnNameCatalog() {
  }
}
