//package declaration
package ch.nolix.systemapi.sqldatabasebasicschemaapi.schemadtoapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//interface
public interface ITableDto {

  // method declaration
  IContainer<IColumnDto> getColumns();

  // method declaration
  String getName();
}
