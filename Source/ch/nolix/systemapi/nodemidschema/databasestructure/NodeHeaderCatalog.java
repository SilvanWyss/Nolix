/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.nodemidschema.databasestructure;

import ch.nolix.baseapi.misc.variablenamecatalog.PascalCaseVariableNameCatalog;

/**
 * @author Silvan Wyss
 */
public final class NodeHeaderCatalog {
  public static final String BACK_REFERENCEABLE_COLUMN_IDS = "BackReferenceableColumnIds";

  public static final String COLUMN = PascalCaseVariableNameCatalog.COLUMN;

  public static final String CONTENT_MODEL = "ContentModel";

  public static final String DATABASE = PascalCaseVariableNameCatalog.DATABASE;

  public static final String DATABASE_PROPERTIES = "DatabaseProperties";

  public static final String DATA_TYPE = PascalCaseVariableNameCatalog.DATA_TYPE;

  public static final String ENTITY = PascalCaseVariableNameCatalog.ENTITY;

  public static final String ENTITY_INDEX = "EntityIndex";

  public static final String ENTITY_INDEXES = "EntityIndexes";

  public static final String FIELD_TYPE = "FieldType";

  public static final String ID = PascalCaseVariableNameCatalog.ID;

  public static final String NAME = PascalCaseVariableNameCatalog.NAME;

  public static final String REFERENCEABLE_TABLE_IDS = "ReferenceableTableIds";

  public static final String SCHEMA_TIMESTAMP = "SchemaTimestamp";

  public static final String TABLE = PascalCaseVariableNameCatalog.TABLE;

  /**
   * Prevents that an instance of the {@link NodeHeaderCatalog} can be created.
   */
  private NodeHeaderCatalog() {
  }
}
