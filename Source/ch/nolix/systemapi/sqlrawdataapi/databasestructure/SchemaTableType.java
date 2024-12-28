package ch.nolix.systemapi.sqlrawdataapi.databasestructure;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.IQualifiedNameHolder;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.coreapi.programatomapi.variableapi.PascalCaseVariableCatalogue;

public enum SchemaTableType implements IQualifiedNameHolder {
  TABLE(PascalCaseVariableCatalogue.TABLE),
  COLUMN(PascalCaseVariableCatalogue.COLUMN),
  TABLE_REFERENCE("TableReference");

  private static final String QUALIFYING_PREFIX = TableType.SCHEMA_TABLE.getQualifyingPrefix();

  private final String name;

  SchemaTableType(final String name) {

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseVariableCatalogue.NAME).isNotBlank();

    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getQualifyingPrefix() {
    return QUALIFYING_PREFIX;
  }
}
