/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlmiddatabasestructure.table;

import ch.nolix.baseapi.generalcatalog.variablenamecatalog.PascalCaseVariableNameCatalog;

/**
 * Of the {@link SchemaTableNameCatalog} an instance cannot be created.
 * 
 * @author Silvan Wyss
 */
public final class SchemaTableNameCatalog {
  public static final String TABLE = PascalCaseVariableNameCatalog.TABLE;

  public static final String COLUMN = PascalCaseVariableNameCatalog.COLUMN;

  public static final String REFERENCEABLE_TABLE = "ReferenceableTable";

  public static final String BACK_REFERENCEABLE_COLUMN = "BackReferenceableColumn";

  /**
   * Prevents that an instance of the {@link SchemaTableNameCatalog} can be
   * created.
   */
  private SchemaTableNameCatalog() {
  }
}
