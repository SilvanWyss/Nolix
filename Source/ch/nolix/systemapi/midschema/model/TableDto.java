/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.midschema.model;

import ch.nolix.baseapi.container.base.IContainer;

public record TableDto(String id, String name, IContainer<ColumnDto> columns) {
}
