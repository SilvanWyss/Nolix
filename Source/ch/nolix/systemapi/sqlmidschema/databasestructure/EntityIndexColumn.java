package ch.nolix.systemapi.sqlmidschema.databasestructure;

import ch.nolix.coreapi.attribute.mandatoryattribute.INameHolder;

/**
 * @author Silvan Wyss
 */
public enum EntityIndexColumn implements INameHolder {
  ENTITY_ID("EntityId"),
  TABLE_ID("TableId");

  private final String name;

  EntityIndexColumn(final String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }
}
