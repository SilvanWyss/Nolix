//package declaration
package ch.nolix.system.sqlrawschema.structure;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.IQualifiedNameHolder;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//enum
public enum MetaDataTableType implements IQualifiedNameHolder {
  DATABASE_PROPERTY("DatabaseProperty");

  //constant
  private static final String QUALIFYING_PREFIX = TableType.META_DATA_TABLE.getQualifyingPrefix();

  //attribute
  private final String name;

  //constructor
  MetaDataTableType(final String name) {

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