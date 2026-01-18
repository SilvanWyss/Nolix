/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.midschemaview.model;

import ch.nolix.coreapi.container.base.IContainer;

/**
 * @author Silvan Wyss
 * @param id
 * @param name
 * @param columnViews
 */
public record TableViewDto(String id, String name, IContainer<ColumnViewDto> columnViews) {
}
