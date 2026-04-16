/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.midschemainfo.model;

import ch.nolix.baseapi.container.base.IContainer;

/**
 * @author Silvan Wyss
 * @param name
 * @param tableViews
 */
public record DatabaseInfoDto(String name, IContainer<TableInfoDto> tableViews) {
}
