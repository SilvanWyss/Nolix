package ch.nolix.systemapi.sqlschemaapi.modelapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.sqlschemaapi.constaintproperty.ColumnConstraint;

public record ConstraintDto(ColumnConstraint type, IContainer<String> parameters) {
}
