//package declaration
package ch.nolix.systemapi.sqlschemaapi.schemadtoapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//interface
public interface ITableDto {

  //method declaration
  IContainer<IColumnDto> getColumns();

  //method declaration
  String getName();
}
