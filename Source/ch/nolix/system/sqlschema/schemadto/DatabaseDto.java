//package declaration
package ch.nolix.system.sqlschema.schemadto;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programatomapi.variablenameapi.LowerCaseCatalogue;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.IDatabaseDto;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.ITableDto;

//class
public record DatabaseDto(String name, ImmutableList<ITableDto> tables) implements IDatabaseDto {

  //constructor
  public DatabaseDto(final String name, final IContainer<ITableDto> tables) {
    this(name, ImmutableList.forIterable(tables));
  }

  //constructor
  public DatabaseDto(final String name, final ImmutableList<ITableDto> tables) { //NOSONAR: This implementations checks
                                                                                 //the given arguments.

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotNull();

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
