package ch.nolix.systemapi.sqlschema.model;

import ch.nolix.coreapi.container.base.IContainer;

public record TableDto(String name, IContainer<ColumnDto> columns) {
}
