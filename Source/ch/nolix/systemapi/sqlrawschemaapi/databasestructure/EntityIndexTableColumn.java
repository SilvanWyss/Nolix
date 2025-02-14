package ch.nolix.systemapi.sqlrawschemaapi.databasestructure;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;

public enum EntityIndexTableColumn implements INameHolder {
  TABLE_ID("TableId"),
  ENTITY_ID("EntityId");

  private final String name;

  EntityIndexTableColumn(final String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }
}
