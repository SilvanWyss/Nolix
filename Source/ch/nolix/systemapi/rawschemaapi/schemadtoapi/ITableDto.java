//package declaration
package ch.nolix.systemapi.rawschemaapi.schemadtoapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//interface
public interface ITableDto {

  //method declaration
  IContainer<IColumnDto> getColumns();

  //method declaration
  String getId();

  //method declaration
  String getName();
}
