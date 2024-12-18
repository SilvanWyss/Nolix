package ch.nolix.systemapi.sqlschemaapi.schemadtoapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.sqlschemaapi.columnconstaintproperty.ConstraintType;

public interface IConstraintDto {

  IContainer<String> getParameters();

  ConstraintType getType();
}
