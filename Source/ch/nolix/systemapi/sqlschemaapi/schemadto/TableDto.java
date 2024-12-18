package ch.nolix.systemapi.sqlschemaapi.schemadto;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public record TableDto(String name, IContainer<ColumnDto> columns) {
}
