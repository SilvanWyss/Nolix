package ch.nolix.systemapi.sqlrawschemaapi.databasestructure;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;
import ch.nolix.coreapi.programatomapi.stringcatalogapi.CharacterCatalog;
import ch.nolix.coreapi.programatomapi.variableapi.PascalCaseVariableCatalog;

public enum DatabasePropertyTableColumn implements INameHolder {

  //'Key' is a reserved word in MSSQL databases.
  KEY("ValueKey"),

  //'Value' is a reserved word in MSSQL databases.
  VALUE(PascalCaseVariableCatalog.VALUE + CharacterCatalog.UNDERSCORE);

  private final String name;

  DatabasePropertyTableColumn(final String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }
}
