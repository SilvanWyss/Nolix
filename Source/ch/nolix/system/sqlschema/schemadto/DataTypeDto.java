package ch.nolix.system.sqlschema.schemadto;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.IDataTypeDto;

public final class DataTypeDto implements IDataTypeDto {

  private final String name;

  private final String parameter;

  public DataTypeDto(final String name) {

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseVariableCatalogue.NAME).isNotNull();

    this.name = name;
    parameter = null;
  }

  public DataTypeDto(final String name, final String parameter) {

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseVariableCatalogue.NAME).isNotNull();
    GlobalValidator.assertThat(parameter).thatIsNamed(LowerCaseVariableCatalogue.PARAMETER).isNotNull();

    this.name = name;
    this.parameter = parameter;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getParameter() {

    assertHasParameter();

    return parameter;
  }

  @Override
  public boolean hasParameter() {
    return (parameter != null);
  }

  private void assertHasParameter() {
    if (!hasParameter()) {
      throw //
      ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableCatalogue.PARAMETER);
    }
  }
}
