package ch.nolix.systemapi.sqlmidschema.databasestructure;

/**
 * Of the {@link ContentModelColumnNameCatalog} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @version 2025-03-28
 */
public final class ContentModelColumnNameCatalog {

  public static final String ID = "Id";

  public static final String FIELD_TYPE = "FieldType";

  public static final String DATA_TYPE = "DataType";

  public static final String REFERENCED_TABLE_ID = "ReferencedTableId";

  public static final String BACK_REFERENCED_COLUM_ID = "BackReferencedColumnId";

  /**
   * Prevents that an instance of the {@link ContentModelColumnNameCatalog} can be
   * created.
   */
  private ContentModelColumnNameCatalog() {
  }
}
