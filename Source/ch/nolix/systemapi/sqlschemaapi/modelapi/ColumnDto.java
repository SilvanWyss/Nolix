package ch.nolix.systemapi.sqlschemaapi.modelapi;

import ch.nolix.coreapi.container.base.IContainer;

public record ColumnDto(String name, DataTypeDto dataType, IContainer<ColumnConstraintDto> constraints) {
}
