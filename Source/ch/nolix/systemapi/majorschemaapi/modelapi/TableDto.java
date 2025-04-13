package ch.nolix.systemapi.majorschemaapi.modelapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public record TableDto(String id, String name, IContainer<ColumnDto> columns) {
}
