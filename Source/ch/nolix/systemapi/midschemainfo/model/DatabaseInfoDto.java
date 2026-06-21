/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.midschemainfo.model;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;

/**
 * @author Silvan Wyss
 * @param name
 * @param tableViews
 */
public record DatabaseInfoDto(String name, ExtendedIterable<TableInfoDto> tableViews) {
}
