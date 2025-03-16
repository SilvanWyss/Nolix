package ch.nolix.systemapi.sqlmidschemaapi.databasestructure;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;
import ch.nolix.coreapi.programatomapi.stringcatalogapi.CharacterCatalog;
import ch.nolix.coreapi.programatomapi.variableapi.PascalCaseVariableCatalog;

public enum TableTableColumn implements INameHolder {
  ID(PascalCaseVariableCatalog.ID + CharacterCatalog.UNDERSCORE),
  NAME(PascalCaseVariableCatalog.NAME);

  private final String name;

  TableTableColumn(final String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }
}
