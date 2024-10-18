package ch.nolix.systemapi.rawschemaapi.schemadtoapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public interface ITableDto {

  IContainer<IColumnDto> getColumns();

  String getId();

  String getName();
}
