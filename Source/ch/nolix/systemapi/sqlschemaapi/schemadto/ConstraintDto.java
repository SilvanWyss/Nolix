package ch.nolix.systemapi.sqlschemaapi.schemadto;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.sqlschemaapi.columnconstaintproperty.ConstraintType;

public record ConstraintDto(ConstraintType type, IContainer<String> parameters) {
}
