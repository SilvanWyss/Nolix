package ch.nolix.systemapi.sqlschemaapi.modelapi;

import ch.nolix.coreapi.container.base.IContainer;

public record ColumnConstraintDto(ColumnConstraint constraint, IContainer<String> parameters) {
}
