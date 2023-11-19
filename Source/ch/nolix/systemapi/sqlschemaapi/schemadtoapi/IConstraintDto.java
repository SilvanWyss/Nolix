//package declaration
package ch.nolix.systemapi.sqlschemaapi.schemadtoapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//interface
public interface IConstraintDto {

  //method declaration
  IContainer<String> getParameters();

  //method declaration
  ConstraintType getType();
}
