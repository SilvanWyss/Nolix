/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlschema.model;

import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;

public record ColumnDto(String name, DataTypeDto dataType, IWellOrderContainer<ColumnConstraintDto> constraints) {
}
