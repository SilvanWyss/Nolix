package ch.nolix.systemapi.sqlrawschemaapi.databasestructure;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.IQualifiedNameHolder;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;
import ch.nolix.coreapi.programatomapi.variableapi.PascalCaseVariableCatalog;

public enum SchemaTableType implements IQualifiedNameHolder {
  TABLE(PascalCaseVariableCatalog.TABLE),
  COLUMN(PascalCaseVariableCatalog.COLUMN),
  TABLE_REFERENCE("TableReference");

  private static final String QUALIFYING_PREFIX = TableType.SCHEMA_TABLE.getQualifyingPrefix();

  private final String name;

  SchemaTableType(final String name) {

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseVariableCatalog.NAME).isNotBlank();

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
