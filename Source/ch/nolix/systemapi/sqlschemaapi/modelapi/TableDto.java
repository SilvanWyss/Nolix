package ch.nolix.systemapi.sqlschemaapi.modelapi;

import ch.nolix.coreapi.container.base.IContainer;

public record TableDto(String name, IContainer<ColumnDto> columns) {
}
