package ch.nolix.systemapi.rawschemaapi.dto;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public record TableDto(String id, String name, IContainer<ColumnDto> columns) {
}
