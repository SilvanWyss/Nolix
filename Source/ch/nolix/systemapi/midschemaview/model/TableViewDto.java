package ch.nolix.systemapi.midschemaview.model;

import ch.nolix.coreapi.container.base.IContainer;

/**
 * @author Silvan Wyss
 * @version 2025-01-10
 * @param id
 * @param name
 * @param columnViews
 */
public record TableViewDto(String id, String name, IContainer<ColumnViewDto> columnViews) {
}
