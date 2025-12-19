package ch.nolix.systemapi.sqlmidschema.databasestructure;

import ch.nolix.coreapi.attribute.mandatoryattribute.INameHolder;

/**
 * @author Silvan Wyss
 */
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
