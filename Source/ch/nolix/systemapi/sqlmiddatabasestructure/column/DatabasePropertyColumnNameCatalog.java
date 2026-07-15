/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlmiddatabasestructure.column;

import ch.nolix.baseapi.generalcatalog.textcatalog.CharacterCatalog;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.PascalCaseVariableNameCatalog;

/**
 * Of the {@link DatabasePropertyColumnNameCatalog} an instance cannot be
 * created.
 * 
 * @author Silvan Wyss
 */
public final class DatabasePropertyColumnNameCatalog {
  // 'Key' is a reserved word in MSSQL.
  public static final String KEY = PascalCaseVariableNameCatalog.KEY + CharacterCatalog.UNDERSCORE;

  // 'Value' is a reserved word in MSSQL.
  public static final String VALUE = PascalCaseVariableNameCatalog.VALUE + CharacterCatalog.UNDERSCORE;

  /**
   * Prevents that an instance of the {@link DatabasePropertyColumnNameCatalog}
   * can be created.
   */
  private DatabasePropertyColumnNameCatalog() {
  }
}
