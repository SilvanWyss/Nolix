/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlmidschema.databasestructure;

import ch.nolix.coreapi.attribute.mandatoryattribute.INameHolder;
import ch.nolix.coreapi.commontypetool.charactertool.CharacterCatalog;
import ch.nolix.coreapi.misc.variable.PascalCaseVariableCatalog;

/**
 * @author Silvan Wyss
 */
public enum DatabasePropertyColumn implements INameHolder {
  //'Key' is a reserved word in MSSQL databases.
  KEY("ValueKey"),

  //'Value' is a reserved word in MSSQL databases.
  VALUE(PascalCaseVariableCatalog.VALUE + CharacterCatalog.UNDERSCORE);

  private final String name;

  DatabasePropertyColumn(final String name) {
    this.name = name;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getName() {
    return name;
  }
}
