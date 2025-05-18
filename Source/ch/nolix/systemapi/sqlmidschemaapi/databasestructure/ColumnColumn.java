package ch.nolix.systemapi.sqlmidschemaapi.databasestructure;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;
import ch.nolix.coreapi.programatomapi.variableapi.PascalCaseVariableCatalog;

public enum ColumnColumn implements INameHolder {
  ID(PascalCaseVariableCatalog.ID),
  PARENT_TABLE_ID("ParentTableId"),
  NAME(PascalCaseVariableCatalog.NAME),
  CONTENT_MODEL("ContentModel"),
  CONTENT_TYPE("ContentType"),
  DATA_TYPE(PascalCaseVariableCatalog.DATA_TYPE),
  BACK_REFERENCED_COLUM_ID("BackReferencedColumnId");

  private final String name;

  ColumnColumn(final String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }
}
