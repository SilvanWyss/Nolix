package ch.nolix.system.sqlrawschema.columntable;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.coreapi.programatomapi.variableapi.PascalCaseVariableCatalogue;

public enum ColumnTableColumn implements INameHolder {
  ID(PascalCaseVariableCatalogue.ID),
  PARENT_TABLE_ID("ParentTableId"),
  NAME(PascalCaseVariableCatalogue.NAME),
  FIELD_TYPE("FieldType"),
  DATA_TYPE(PascalCaseVariableCatalogue.DATA_TYPE),
  REFERENCED_TABLE_ID("ReferencedTableId"),
  BACK_REFERENCED_COLUM_ID("BackReferencedColumnId");

  private final String name;

  ColumnTableColumn(final String name) {

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseVariableCatalogue.NAME).isNotBlank();

    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }
}
