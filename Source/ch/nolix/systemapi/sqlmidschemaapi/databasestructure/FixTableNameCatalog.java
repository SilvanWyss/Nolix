package ch.nolix.systemapi.sqlmidschemaapi.databasestructure;

import ch.nolix.coreapi.programatomapi.variableapi.PascalCaseVariableCatalog;

/**
 * Of the {@link FixTableNameCatalog} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @version 2025-03-22
 */
public final class FixTableNameCatalog {

  public static final String DATABASE_PROPERTY = "DatabaseProperty";

  public static final String TABLE = PascalCaseVariableCatalog.TABLE;

  public static final String COLUMN = PascalCaseVariableCatalog.COLUMN;

  public static final String ENTITY_INDEX = "EntityIndex";

  public static final String MULTI_VALUE_ENTRY = "MultiValueEntry";

  public static final String MULTI_REFERENCE_ENTRY = "MultiReferenceEntry";

  public static final String MULTI_BACK_REFERENCE_ENTRY = "MultiBackReferenceEntry";

  /**
   * Prevents that an instance of the {@link FixTableNameCatalog} can be created.
   */
  private FixTableNameCatalog() {
  }
}
