//package declaration
package ch.nolix.systemapi.sqlschemaapi.schemadtoapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//interface
public interface IDatabaseDto {

  //method declaration
  String getName();

  //method declaration
  IContainer<ITableDto> getTables();
}
