/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlmidschema.databasestructure;

import ch.nolix.baseapi.commontypetool.charactertool.CharacterCatalog;
import ch.nolix.baseapi.misc.variable.PascalCaseVariableCatalog;

/**
 * @author Silvan Wyss
 */
public enum DatabasePropertyColumn {
  //'Key' is a reserved word in MSSQL.
  KEY(PascalCaseVariableCatalog.KEY + CharacterCatalog.UNDERSCORE),

  //'Value' is a reserved word in MSSQL.
  VALUE(PascalCaseVariableCatalog.VALUE + CharacterCatalog.UNDERSCORE);

  private final String stringRepresentation;

  /**
   * Creates a new {@link DatabasePropertyColumn} with the given
   * stringRepresentation.
   * 
   * @param stringRepresentation
   */
  DatabasePropertyColumn(final String stringRepresentation) {
    this.stringRepresentation = stringRepresentation;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return stringRepresentation;
  }
}
