/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.midschemainfo.model;

import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;

/**
 * @author Silvan Wyss
 * @param name
 * @param tableViews
 */
public record DatabaseInfoDto(String name, IWellOrderContainer<TableInfoDto> tableViews) {
}
