package ch.nolix.systemapi.sqlschemaapi.modelapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public record TableDto(String name, IContainer<ColumnDto> columns) {
}
