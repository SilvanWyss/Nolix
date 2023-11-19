//package declaration
package ch.nolix.system.sqlschema.schemadto;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.container.readcontainer.ReadContainer;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programatomapi.variablenameapi.LowerCaseCatalogue;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.IColumnDto;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.ITableDto;

//class
public final class TableDto implements ITableDto {

  //attribute
  private final String name;

  //mutli-attribute
  private final IContainer<IColumnDto> columns;

  //constructor
  public TableDto(final String name, final IColumnDto column, final IColumnDto... columns) {
    this(name, ReadContainer.forElement(column, columns));
  }

  //constructor
  public TableDto(final String name, final IContainer<IColumnDto> columns) {

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotNull();

    this.name = name;

    this.columns = LinkedList.fromIterable(columns);
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
