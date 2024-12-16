package ch.nolix.systemapi.rawschemaapi.schemadtoapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.rawschemaapi.schemadto.TableDto;

public interface IDatabaseDto {

  String getName();

  IContainer<TableDto> getTables();
}
