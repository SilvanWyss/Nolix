//package declaration
package ch.nolix.system.sqlrawschema.tabletype;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.IQualifiedNameHolder;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//enum
public enum MultiEntryTableType implements IQualifiedNameHolder {
  MULTI_VALUE_ENTRY("MultiValueEntry"),
  MULTI_REFERENCE_ENTRY("MultiReferenceEntry"),
  MULTI_BACK_REFERENCE_ENTRY("MultiBackReferenceEntry");

  //constant
  private static final String QUALIFYING_PREFIX = TableType.MULTI_ENTRY_TABLE.getQualifyingPrefix();

  //attribute
  private final String name;

  //constructor
  MultiEntryTableType(final String name) {

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseVariableCatalogue.NAME).isNotBlank();

    this.name = name;
  }

  //method
  @Override
  public String getName() {
    return name;
  }

  //method
  @Override
  public String getQualifyingPrefix() {
    return QUALIFYING_PREFIX;
  }
}
