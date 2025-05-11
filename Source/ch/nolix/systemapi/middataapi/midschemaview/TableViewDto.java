package ch.nolix.systemapi.middataapi.midschemaview;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

/**
 * @author Silvan Wyss
 * @version 2025-01-10
 * @param id
 * @param name
 * @param columnSchemaViews
 */
public record TableViewDto(String id, String name, IContainer<ColumnViewDto> columnSchemaViews) {
}
