package ch.nolix.systemapi.sqlmidschemaapi.databasestructure;

/**
 * Of the {@link MultiBackReferenceEntryColumnNameCatalog} an instance cannot be
 * created.
 * 
 * @author Silvan Wyss
 * @version 2025-06-19
 */
public final class MultiBackReferenceEntryColumnNameCatalog {

  public static final String ENTITY_ID = "EntityId";

  public static final String MULTI_BACK_REFERENCE_COLUMN_ID = "MultiBackReferenceColumnId";

  public static final String BACK_REFERENCED_ENTITY_ID = "BackReferencedEntityId";

  public static final String BACK_REFERENCED_ENTITY_TABLE_ID = "BackReferencedEntityTableId";

  /**
   * Prevents that an instance of the
   * {@link MultiBackReferenceEntryColumnNameCatalog} can be created.
   */
  private MultiBackReferenceEntryColumnNameCatalog() {
  }
}
