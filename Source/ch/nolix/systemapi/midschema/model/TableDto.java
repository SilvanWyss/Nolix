package ch.nolix.systemapi.midschema.model;

import ch.nolix.coreapi.container.base.IContainer;

public record TableDto(String id, String name, IContainer<ColumnDto> columns) {
}
