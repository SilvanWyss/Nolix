//package declaration
package ch.nolix.system.objectschema.flatschemadto;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.systemapi.rawschemaapi.flatschemadtoapi.IFlatDatabaseDto;

//class
public record FlatDatabaseDto(String name) implements IFlatDatabaseDto {

  //constructor
  //For a better performance, this implementation does not use all comfortable methods.
  public FlatDatabaseDto(final String name) { //NOSONAR: This implementations checks the given arguments.

    if (name == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalogue.NAME);
    }

    this.name = name;
  }

  //method
  @Override
  public String getName() {
    return name;
  }
}
