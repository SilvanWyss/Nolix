package ch.nolix.systemapi.sqlmidschema.databasestructure;

/**
 * Of the {@link MultiReferenceEntryColumnNameCatalog} an instance cannot be
 * created.
 * 
 * @author Silvan Wyss
 */
public final class MultiReferenceEntryColumnNameCatalog {
  public static final String ENTITY_ID = "EntityId";

  public static final String ENTITY_TABLE_ID = "EntityTableId";

  public static final String MULTI_REFERENCE_COLUMN_ID = "MultiReferenceColumnId";

  public static final String REFERENCED_ENTITY_ID = "ReferencedEntityId";

  public static final String REFERENCED_ENTITY_TABLE_ID = "ReferencedEntityTableId";

  /**
   * Prevents that an instance of the {@link MultiReferenceEntryColumnNameCatalog}
   * can be created.
   */
  private MultiReferenceEntryColumnNameCatalog() {
  }
}
