//package declaration
package ch.nolix.system.sqldatabasebasicschema.schemadto;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.variablenameapi.LowerCaseCatalogue;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.IDataTypeDto;

//class
public final class DataTypeDto implements IDataTypeDto {

  //attribute
  private final String name;

  //optional attribute
  private final String parameter;

  //constructor
  public DataTypeDto(final String name) {

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotNull();

    this.name = name;
    parameter = null;
  }

  //constructor
  public DataTypeDto(final String name, final String parameter) {

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotNull();
    GlobalValidator.assertThat(parameter).thatIsNamed(LowerCaseCatalogue.PARAMETER).isNotNull();

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
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseCatalogue.PARAMETER);
    }
  }
}
