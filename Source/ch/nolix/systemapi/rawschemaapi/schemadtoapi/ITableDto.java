package ch.nolix.systemapi.rawschemaapi.schemadtoapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.rawschemaapi.schemadto.ColumnDto;

public interface ITableDto {

  IContainer<ColumnDto> getColumns();

  String getId();

  String getName();
}
