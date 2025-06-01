package ch.nolix.systemapi.sqlschemaapi.modelapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public record ColumnConstraintDto(ColumnConstraint constraint, IContainer<String> parameters) {
}
