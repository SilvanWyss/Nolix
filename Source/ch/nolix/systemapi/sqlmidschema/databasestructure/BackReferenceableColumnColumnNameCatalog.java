package ch.nolix.systemapi.sqlmidschema.databasestructure;

/**
 * Of the {@link BackReferenceableColumnColumnNameCatalog} an instance cannot be
 * created.
 * 
 * @author Silvan Wyss
 * @version 2025-09-20
 */
public final class BackReferenceableColumnColumnNameCatalog {
  public static final String PARENT_BASE_BACK_REFERENCE_COLUMN_ID = "ParentBaseBackReferenceColumnId";

  public static final String BACK_REFERENCEABLE_COLUMN_ID = "BackReferenceableColumnId";

  /**
   * Prevents that an instance of the
   * {@link BackReferenceableColumnColumnNameCatalog} can be created.
   */
  private BackReferenceableColumnColumnNameCatalog() {
  }
}
