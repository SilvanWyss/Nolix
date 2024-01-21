//package declaration
package ch.nolix.system.objectschema.flatschemadto;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.systemapi.rawschemaapi.flatschemadtoapi.IFlatTableDto;

//class
public record FlatTableDto(String id, String name) implements IFlatTableDto {

  //constructor
  //For a better performance, this implementation does not use all comfortable
  //methods.
  public FlatTableDto(final String id, final String name) { //NOSONAR: This implementations checks the given arguments.

    if (id == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalogue.ID);
    }

    if (name == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalogue.NAME);
    }

    this.id = id;
    this.name = name;
  }

  //method
  @Override
  public String getId() {
    return id;
  }

  //method
  @Override
  public String getName() {
    return name;
  }
}
