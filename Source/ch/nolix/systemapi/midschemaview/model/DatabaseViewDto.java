package ch.nolix.systemapi.midschemaview.model;

import ch.nolix.coreapi.container.base.IContainer;

/**
 * @author Silvan Wyss
 * @version 2025-01-31
 * @param name
 * @param tableViews
 */
public record DatabaseViewDto(String name, IContainer<TableViewDto> tableViews) {
}
