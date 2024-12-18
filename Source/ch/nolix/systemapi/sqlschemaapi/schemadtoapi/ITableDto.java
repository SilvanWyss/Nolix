package ch.nolix.systemapi.sqlschemaapi.schemadtoapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.sqlschemaapi.schemadto.ColumnDto;

public interface ITableDto {

  IContainer<ColumnDto> getColumns();

  String getName();
}
