/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.midschemainfo.model;

import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;

/**
 * @author Silvan Wyss
 * @param id
 * @param name
 * @param columnViews
 */
public record TableInfoDto(String id, String name, IWellOrderContainer<ColumnInfoDto> columnViews) {
}
