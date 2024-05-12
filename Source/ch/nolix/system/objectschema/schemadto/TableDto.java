//package declaration
package ch.nolix.system.objectschema.schemadto;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDto;

//class
public final class TableDto implements ITableDto {

  //attribute
  private final String id;

  //attribute
  private final String name;

  //multi-attribute
  private final IContainer<IColumnDto> columnDtos;

  //constructor
  //For a better performance, this implementation does not use all comfortable methods.
  public TableDto(
    final String id,
    final String name,
    final IContainer<IColumnDto> columnDtos) {

    if (id == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalogue.ID);
    }

    if (name == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalogue.NAME);
    }

    if (columnDtos == null) {
      throw ArgumentIsNullException.forArgumentName("column DTOs");
    }

    this.id = id;
    this.name = name;
    this.columnDtos = LinkedList.fromIterable(columnDtos);
  }

  //method
  @Override
  public IContainer<IColumnDto> getColumns() {
    return columnDtos;
  }

  //method
  @Override
  public String getId() {
    return id;
  }

  //method
  @Override
  public String getName() {
    return name;
  }
}
