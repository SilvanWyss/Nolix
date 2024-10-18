package ch.nolix.system.sqlrawschema.structure;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.IQualifiedNameHolder;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

public enum IndexTableType implements IQualifiedNameHolder {
  ENTITY_HEAD("EntityHead");

  private static final String QUALIFYING_PREFIX = TableType.INDEX_TABLE.getQualifyingPrefix();

  private final String name;

  IndexTableType(final String name) {

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
