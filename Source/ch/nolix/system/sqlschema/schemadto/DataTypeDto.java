//package declaration
package ch.nolix.system.sqlschema.schemadto;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.IDataTypeDto;

//class
public final class DataTypeDto implements IDataTypeDto {

  //attribute
  private final String name;

  //optional attribute
  private final String parameter;

  //constructor
  public DataTypeDto(final String name) {

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseVariableCatalogue.NAME).isNotNull();

    this.name = name;
    parameter = null;
  }

  //constructor
  public DataTypeDto(final String name, final String parameter) {

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseVariableCatalogue.NAME).isNotNull();
    GlobalValidator.assertThat(parameter).thatIsNamed(LowerCaseVariableCatalogue.PARAMETER).isNotNull();

    this.name = name;
    this.parameter = parameter;
  }

  //method
  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getParameter() {

    assertHasParameter();

    return parameter;
  }

  //method
  @Override
  public boolean hasParameter() {
    return (parameter != null);
  }

  //method
  private void assertHasParameter() {
    if (!hasParameter()) {
      throw //
      ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableCatalogue.PARAMETER);
    }
  }
}
