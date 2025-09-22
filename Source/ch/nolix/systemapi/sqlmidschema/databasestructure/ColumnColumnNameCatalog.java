package ch.nolix.systemapi.sqlmidschema.databasestructure;

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

  public static final String FIELD_TYPE = "FieldType";

  public static final String DATA_TYPE = "DataType";

  /**
   * Prevents that an instance of the {@link ColumnColumnNameCatalog} can be
   * created.
   */
  private ColumnColumnNameCatalog() {
  }
}
