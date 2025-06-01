package ch.nolix.systemapi.sqlschemaapi.modelapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public record ConstraintDto(ColumnConstraint constraint, IContainer<String> parameters) {
}
