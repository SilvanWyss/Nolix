package ch.nolix.systemapi.sqlschemaapi.schemadtoapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.sqlschemaapi.schemadto.ConstraintDto;
import ch.nolix.systemapi.sqlschemaapi.schemadto.DataTypeDto;

public interface IColumnDto {

  IContainer<ConstraintDto> getConstraints();

  DataTypeDto getDataType();

  String getName();
}
