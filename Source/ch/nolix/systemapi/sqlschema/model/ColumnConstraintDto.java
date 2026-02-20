/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlschema.model;

import ch.nolix.baseapi.container.base.IContainer;

public record ColumnConstraintDto(ColumnConstraint constraint, IContainer<String> parameters) {
}
