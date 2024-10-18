package ch.nolix.systemapi.sqlschemaapi.schemadtoapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public interface IConstraintDto {

  IContainer<String> getParameters();

  ConstraintType getType();
}
