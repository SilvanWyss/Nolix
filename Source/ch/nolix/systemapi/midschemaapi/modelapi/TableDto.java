package ch.nolix.systemapi.midschemaapi.modelapi;

import ch.nolix.coreapi.container.base.IContainer;

public record TableDto(String id, String name, IContainer<ColumnDto> columns) {
}
