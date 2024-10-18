package ch.nolix.systemapi.rawschemaapi.schemadtoapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public interface IDatabaseDto {

  String getName();

  IContainer<ITableDto> getTables();
}
