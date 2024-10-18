package ch.nolix.system.sqlschema.schemadto;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.IDatabaseDto;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.ITableDto;

public record DatabaseDto(String name, ImmutableList<ITableDto> tables) implements IDatabaseDto {

  public DatabaseDto(final String name, final IContainer<ITableDto> tables) {
    this(name, ImmutableList.forIterable(tables));
  }

  public DatabaseDto(final String name, final ImmutableList<ITableDto> tables) { //NOSONAR: This implementations checks
                                                                                 //the given arguments.

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseVariableCatalogue.NAME).isNotNull();

    this.name = name;

    this.tables = tables;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public IContainer<ITableDto> getTables() {
    return tables;
  }
}
