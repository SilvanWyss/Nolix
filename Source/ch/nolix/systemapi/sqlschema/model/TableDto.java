/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlschema.model;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;

public record TableDto(String name, ExtendedIterable<ColumnDto> columns) {
}
