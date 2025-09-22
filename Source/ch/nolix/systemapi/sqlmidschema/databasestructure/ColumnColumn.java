package ch.nolix.systemapi.sqlmidschema.databasestructure;

import ch.nolix.coreapi.attribute.mandatoryattribute.INameHolder;
import ch.nolix.coreapi.misc.variable.PascalCaseVariableCatalog;

public enum ColumnColumn implements INameHolder {
  ID(PascalCaseVariableCatalog.ID),
  PARENT_TABLE_ID("ParentTableId"),
  NAME(PascalCaseVariableCatalog.NAME),
  FIELD_TYPE("FieldType"),
  DATA_TYPE(PascalCaseVariableCatalog.DATA_TYPE);

  private final String name;

  ColumnColumn(final String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }
}
