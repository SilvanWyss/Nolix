package ch.nolix.systemapi.sqlschemaapi.schemadtoapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public interface ITableDto {

  IContainer<IColumnDto> getColumns();

  String getName();
}
