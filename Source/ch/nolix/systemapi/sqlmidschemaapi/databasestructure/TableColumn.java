package ch.nolix.systemapi.sqlmidschemaapi.databasestructure;

import ch.nolix.coreapi.attribute.mandatoryattribute.INameHolder;

public enum TableColumn implements INameHolder {
  ID(TableColumnNameCatalog.ID),
  NAME(TableColumnNameCatalog.NAME);

  private final String name;

  TableColumn(final String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }
}
