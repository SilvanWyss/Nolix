//package declaration
package ch.nolix.system.sqlrawschema.tabletype;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.IQualifiedNameHolder;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//enum
public enum IndexTableType implements IQualifiedNameHolder {
  ENTITY_HEAD("EntityHead");

  //constant
  private static final String QUALIFYING_PREFIX = TableType.INDEX_TABLE.getQualifyingPrefix();

  //attribute
  private final String name;

  //constructor
  IndexTableType(final String name) {

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
