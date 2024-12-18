package ch.nolix.systemapi.sqlschemaapi.schemadto;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public record ColumnDto(String name, DataTypeDto dataType, IContainer<ConstraintDto> constraints) {
}
