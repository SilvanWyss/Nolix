package ch.nolix.system.objectschema.flatschemadto;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.systemapi.rawschemaapi.flatschemadtoapi.IFlatTableDto;

public record FlatTableDto(String id, String name) implements IFlatTableDto {

  //For a better performance, this implementation does not use all comfortable methods.
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

  @Override
  public String getId() {
    return id;
  }

  @Override
  public String getName() {
    return name;
  }
}
