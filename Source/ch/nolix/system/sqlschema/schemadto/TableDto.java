//package declaration
package ch.nolix.system.sqlschema.schemadto;

import ch.nolix.core.container.containerview.ContainerView;
//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.IColumnDto;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.ITableDto;

//class
public final class TableDto implements ITableDto {

  //attribute
  private final String name;

  //mutli-attribute
  private final IContainer<IColumnDto> columns;

  //constructor
  private TableDto(final String name, final IContainer<IColumnDto> columns) {

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseVariableCatalogue.NAME).isNotNull();

    this.name = name;

    this.columns = LinkedList.fromIterable(columns);
  }

  //static method
  public static TableDto withNameAndColumn(final String name, final IColumnDto column, final IColumnDto... columns) {

    final var allColumns = ContainerView.forElementAndArray(column, columns);

    return withNameAndColumns(name, allColumns);
  }

  //static method
  public static TableDto withNameAndColumns(final String name, final IContainer<IColumnDto> columns) {
    return new TableDto(name, columns);
  }

  //method
  @Override
  public IContainer<IColumnDto> getColumns() {
    return columns;
  }

  //method
  @Override
  public String getName() {
    return name;
  }
}
