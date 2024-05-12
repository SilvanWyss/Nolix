//package declaration
package ch.nolix.system.objectschema.schemadto;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.coreapi.programatomapi.variableapi.PluralLowerCaseVariableCatalogue;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IDatabaseDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDto;

//class
public record DatabaseDto(String name, ImmutableList<ITableDto> tables) implements IDatabaseDto {

  //constructor
  public DatabaseDto(final String name, final IContainer<ITableDto> tables) {
    this(name, ImmutableList.forIterable(tables));
  }

  //constructor
  //For a better performance, this implementation does not use all comfortable methods.
  public DatabaseDto(final String name, final ImmutableList<ITableDto> tables) { //NOSONAR: This implementations checks
                                                                                 //the given arguments.

    if (name == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalogue.NAME);
    }

    if (tables == null) {
      throw ArgumentIsNullException.forArgumentName(PluralLowerCaseVariableCatalogue.TABLES);
    }

    this.name = name;
    this.tables = tables;
  }

  //method
  @Override
  public String getName() {
    return name;
  }

  //method
  @Override
  public IContainer<ITableDto> getTables() {
    return tables;
  }
}
