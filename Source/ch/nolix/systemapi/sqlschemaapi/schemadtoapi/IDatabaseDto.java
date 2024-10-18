package ch.nolix.systemapi.sqlschemaapi.schemadtoapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public interface IDatabaseDto {

  String getName();

  IContainer<ITableDto> getTables();
}
