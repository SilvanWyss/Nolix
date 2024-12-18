package ch.nolix.systemapi.sqlschemaapi.schemadto;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.IColumnDto;

public record TableDto(String name, IContainer<IColumnDto> columns) {
}
