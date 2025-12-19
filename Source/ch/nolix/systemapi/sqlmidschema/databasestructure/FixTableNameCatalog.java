package ch.nolix.systemapi.sqlmidschema.databasestructure;

import ch.nolix.coreapi.misc.variable.PascalCaseVariableCatalog;

/**
 * Of the {@link FixTableNameCatalog} an instance cannot be created.
 * 
 * @author Silvan Wyss
 */
public final class FixTableNameCatalog {
  public static final String DATABASE_PROPERTY = "DatabaseProperty";

  public static final String TABLE = PascalCaseVariableCatalog.TABLE;

  public static final String COLUMN = PascalCaseVariableCatalog.COLUMN;

  public static final String CONTENT_MODEL = "ContentModel";

  public static final String REFERENCEABLE_TABLE = "ReferenceableTable";

  public static final String BACK_REFERENCEABLE_COLUMN = "BackReferenceableColumn";

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
