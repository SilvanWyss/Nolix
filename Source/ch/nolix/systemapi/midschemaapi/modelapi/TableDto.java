package ch.nolix.systemapi.midschemaapi.modelapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public record TableDto(String id, String name, IContainer<ColumnDto> columns) {
}
