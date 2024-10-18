package ch.nolix.systemapi.sqlschemaapi.schemadtoapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public interface IColumnDto {

  IContainer<IConstraintDto> getConstraints();

  IDataTypeDto getDataType();

  String getName();
}
