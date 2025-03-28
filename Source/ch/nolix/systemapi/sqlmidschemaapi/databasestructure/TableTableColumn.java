package ch.nolix.systemapi.sqlmidschemaapi.databasestructure;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;

public enum TableTableColumn implements INameHolder {
  ID(TableColumnNameCatalog.ID),
  NAME(TableColumnNameCatalog.NAME);

  private final String name;

  TableTableColumn(final String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }
}
