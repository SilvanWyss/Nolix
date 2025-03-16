package ch.nolix.systemapi.sqlmidschemaapi.databasestructure;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;

public enum EntityIndexTableColumn implements INameHolder {
  ENTITY_ID("EntityId"),
  TABLE_ID("TableId");

  private final String name;

  EntityIndexTableColumn(final String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }
}
