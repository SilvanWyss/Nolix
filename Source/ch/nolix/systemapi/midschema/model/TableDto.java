/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.midschema.model;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;

public record TableDto(String id, String name, ExtendedIterable<ColumnDto> columns) {
}
