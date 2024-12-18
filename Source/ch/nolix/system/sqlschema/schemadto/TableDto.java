package ch.nolix.system.sqlschema.schemadto;

import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.systemapi.sqlschemaapi.schemadto.ColumnDto;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.ITableDto;

public final class TableDto implements ITableDto {

  private final String name;

  //mutli-attribute
  private final IContainer<ColumnDto> columns;

  private TableDto(final String name, final IContainer<ColumnDto> columns) {

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseVariableCatalogue.NAME).isNotNull();

    this.name = name;

    this.columns = LinkedList.fromIterable(columns);
  }

  public static TableDto withNameAndColumn(final String name, final ColumnDto column, final ColumnDto... columns) {

    final var allColumns = ContainerView.forElementAndArray(column, columns);

    return withNameAndColumns(name, allColumns);
  }

  public static TableDto withNameAndColumns(final String name, final IContainer<ColumnDto> columns) {
    return new TableDto(name, columns);
  }

  @Override
  public IContainer<ColumnDto> getColumns() {
    return columns;
  }

  @Override
  public String getName() {
    return name;
  }
}
