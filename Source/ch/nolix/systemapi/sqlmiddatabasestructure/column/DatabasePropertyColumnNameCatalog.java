/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlmiddatabasestructure.column;

import ch.nolix.baseapi.commontypetool.charactertool.CharacterCatalog;
import ch.nolix.baseapi.misc.variable.PascalCaseVariableCatalog;

/**
 * Of the {@link DatabasePropertyColumnNameCatalog} an instance cannot be
 * created.
 * 
 * @author Silvan Wyss
 */
public final class DatabasePropertyColumnNameCatalog {
  //'Key' is a reserved word in MSSQL.
  public static final String KEY = PascalCaseVariableCatalog.KEY + CharacterCatalog.UNDERSCORE;

  //'Value' is a reserved word in MSSQL.
  public static final String VALUE = PascalCaseVariableCatalog.VALUE + CharacterCatalog.UNDERSCORE;

  /**
   * Prevents that an instance of the {@link DatabasePropertyColumnNameCatalog}
   * can be created.
   */
  private DatabasePropertyColumnNameCatalog() {
  }
}
