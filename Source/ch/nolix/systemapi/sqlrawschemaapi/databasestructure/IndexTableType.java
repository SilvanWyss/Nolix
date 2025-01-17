package ch.nolix.systemapi.sqlrawschemaapi.databasestructure;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.IQualifiedNameHolder;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;

public enum IndexTableType implements IQualifiedNameHolder {
  ENTITY_HEAD("EntityHead");

  private static final String QUALIFYING_PREFIX = TableType.INDEX_TABLE.getTableNameQualifyingPrefix();

  private final String name;

  IndexTableType(final String name) {

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
