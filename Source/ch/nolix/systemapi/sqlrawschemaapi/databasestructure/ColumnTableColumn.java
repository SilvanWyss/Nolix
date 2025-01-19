package ch.nolix.systemapi.sqlrawschemaapi.databasestructure;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;
import ch.nolix.coreapi.programatomapi.variableapi.PascalCaseVariableCatalog;

public enum ColumnTableColumn implements INameHolder {
  ID(PascalCaseVariableCatalog.ID),
  PARENT_TABLE_ID("ParentTableId"),
  NAME(PascalCaseVariableCatalog.NAME),
  CONTENT_TYPE("ContentType"),
  DATA_TYPE(PascalCaseVariableCatalog.DATA_TYPE),
  REFERENCED_TABLE_ID("ReferencedTableId"),
  BACK_REFERENCED_COLUM_ID("BackReferencedColumnId");

  private final String name;

  ColumnTableColumn(final String name) {

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseVariableCatalog.NAME).isNotBlank();

    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }
}
