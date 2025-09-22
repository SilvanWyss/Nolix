package ch.nolix.systemapi.sqlmidschema.databasestructure;

import ch.nolix.coreapi.attribute.mandatoryattribute.INameHolder;

public enum ColumnColumn implements INameHolder {
  ID(ColumnColumnNameCatalog.ID),
  PARENT_TABLE_ID(ColumnColumnNameCatalog.PARENT_TABLE_ID),
  NAME(ColumnColumnNameCatalog.NAME),
  FIELD_TYPE(ColumnColumnNameCatalog.FIELD_TYPE),
  DATA_TYPE(ColumnColumnNameCatalog.DATA_TYPE);

  private final String name;

  ColumnColumn(final String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }
}
