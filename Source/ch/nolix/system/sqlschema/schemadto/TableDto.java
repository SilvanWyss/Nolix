package ch.nolix.system.sqlschema.schemadto;

import ch.nolix.core.container.containerview.ContainerView;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.IColumnDto;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.ITableDto;

public final class TableDto implements ITableDto {

  private final String name;

  //mutli-attribute
  private final IContainer<IColumnDto> columns;

  private TableDto(final String name, final IContainer<IColumnDto> columns) {

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseVariableCatalogue.NAME).isNotNull();

    this.name = name;

    this.columns = LinkedList.fromIterable(columns);
  }

  public static TableDto withNameAndColumn(final String name, final IColumnDto column, final IColumnDto... columns) {

    final var allColumns = ContainerView.forElementAndArray(column, columns);

    return withNameAndColumns(name, allColumns);
  }

  public static TableDto withNameAndColumns(final String name, final IContainer<IColumnDto> columns) {
    return new TableDto(name, columns);
  }

  @Override
  public IContainer<IColumnDto> getColumns() {
    return columns;
  }

  @Override
  public String getName() {
    return name;
  }
}
