package ch.nolix.systemapi.noderawschemaapi.databasestructureapi;

import ch.nolix.coreapi.programatomapi.variableapi.PascalCaseVariableCatalogue;

/**
 * @author Silvan Wyss
 * @version 2024-12-21
 */
public final class NodeHeaderCatalogue {

  public static final String BACK_REFERENCED_COLUMN_ID = "BackReferencedColumnId";

  public static final String COLUMN = PascalCaseVariableCatalogue.COLUMN;

  public static final String CONTENT_MODEL = "ContentModel";

  public static final String CONTENT_TYPE = "ContentType";

  public static final String DATABASE = PascalCaseVariableCatalogue.DATABASE;

  public static final String DATABASE_PROPERTIES = "DatabaseProperties";

  public static final String DATA_TYPE = PascalCaseVariableCatalogue.DATA_TYPE;

  public static final String ENTITY = PascalCaseVariableCatalogue.ENTITY;

  public static final String ID = PascalCaseVariableCatalogue.ID;

  public static final String NAME = PascalCaseVariableCatalogue.NAME;

  public static final String SCHEMA_TIMESTAMP = "SchemaTimestamp";

  public static final String TABLE = PascalCaseVariableCatalogue.TABLE;

  /**
   * Prevents that an instance of the {@link NodeHeaderCatalogue} can be created.
   */
  private NodeHeaderCatalogue() {
  }
}
