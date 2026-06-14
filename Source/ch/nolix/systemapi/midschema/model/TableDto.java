/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.midschema.model;

import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;

public record TableDto(String id, String name, IWellOrderContainer<ColumnDto> columns) {
}
