//package declaration
package ch.nolix.system.sqlschema.flatschemadto;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.systemapi.sqlschemaapi.flatschemadtoapi.IFlatTableDto;

//class
public record FlatTableDto(String name) implements IFlatTableDto {

  //constructor
  public FlatTableDto(final String name) { //NOSONAR: This implementations checks the given arguments.

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseVariableCatalogue.NAME).isNotNull();

    this.name = name;
  }

  //method
  @Override
  public String getName() {
    return name;
  }
}
