package ch.nolix.systemapi.nodemidschema.databasestructure;

import ch.nolix.coreapi.misc.variable.PascalCaseVariableCatalog;

/**
 * @author Silvan Wyss
 * @version 2024-12-21
 */
public final class NodeHeaderCatalog {

  public static final String BACK_REFERENCED_COLUMN_ID = "BackReferencedColumnId";

  public static final String COLUMN = PascalCaseVariableCatalog.COLUMN;

  public static final String CONTENT_MODEL = "ContentModel";

  public static final String DATABASE = PascalCaseVariableCatalog.DATABASE;

  public static final String DATABASE_PROPERTIES = "DatabaseProperties";

  public static final String DATA_TYPE = PascalCaseVariableCatalog.DATA_TYPE;

  public static final String ENTITY = PascalCaseVariableCatalog.ENTITY;

  public static final String ENTITY_INDEX = "EntityIndex";

  public static final String ENTITY_INDEXES = "EntityIndexes";

  public static final String FIELD_TYPE = "FieldType";

  public static final String ID = PascalCaseVariableCatalog.ID;

  public static final String NAME = PascalCaseVariableCatalog.NAME;

  public static final String REFERENCED_TABLE_ID = "ReferencedTableId";

  public static final String SCHEMA_TIMESTAMP = "SchemaTimestamp";

  public static final String TABLE = PascalCaseVariableCatalog.TABLE;

  /**
   * Prevents that an instance of the {@link NodeHeaderCatalog} can be created.
   */
  private NodeHeaderCatalog() {
  }
}
