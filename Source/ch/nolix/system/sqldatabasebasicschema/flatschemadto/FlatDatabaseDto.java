//package declaration
package ch.nolix.system.sqldatabasebasicschema.flatschemadto;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.variablenameapi.LowerCaseCatalogue;
import ch.nolix.systemapi.sqlschemaapi.flatschemadtoapi.IFlatDatabaseDto;

//class
public record FlatDatabaseDto(String name) implements IFlatDatabaseDto {

  //constructor
  public FlatDatabaseDto(final String name) { //NOSONAR: This implementations checks the given arguments.

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotNull();

    this.name = name;
  }

  //method
  @Override
  public String getName() {
    return name;
  }
}
