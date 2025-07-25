package ch.nolix.systemapi.sqlschema.model;

import ch.nolix.coreapi.container.base.IContainer;

public record ColumnDto(String name, DataTypeDto dataType, IContainer<ColumnConstraintDto> constraints) {
}
