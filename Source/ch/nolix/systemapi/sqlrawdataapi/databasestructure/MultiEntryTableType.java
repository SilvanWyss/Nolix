package ch.nolix.systemapi.sqlrawdataapi.databasestructure;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.IQualifiedNameHolder;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

public enum MultiEntryTableType implements IQualifiedNameHolder {
  MULTI_VALUE_ENTRY("MultiValueEntry"),
  MULTI_REFERENCE_ENTRY("MultiReferenceEntry"),
  MULTI_BACK_REFERENCE_ENTRY("MultiBackReferenceEntry");

  private static final String QUALIFYING_PREFIX = TableType.MULTI_ENTRY_TABLE.getQualifyingPrefix();

  private final String name;

  MultiEntryTableType(final String name) {

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
