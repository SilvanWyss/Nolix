package ch.nolix.systemapi.sqlmidschema.databasestructure;

/**
 * Of the {@link ReferenceableTableNameCatalog} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @version 2025-09-05
 */
public final class ReferenceableTableNameCatalog {
  public static final String PARENT_BASE_REFERENCE_COLUMN_ID = "ParentBaseReferenceColumnId";

  public static final String REFERENCEABLE_TABLE_ID = "ReferenceableTableId";

  /**
   * Prevents that an instance of the {@link ReferenceableTableNameCatalog} can be
   * created.
   */
  private ReferenceableTableNameCatalog() {
  }
}
