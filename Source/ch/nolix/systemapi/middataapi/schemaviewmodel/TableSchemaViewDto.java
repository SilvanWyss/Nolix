package ch.nolix.systemapi.middataapi.schemaviewmodel;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

/**
 * @author Silvan Wyss
 * @version 2025-01-10
 * @param id
 * @param name
 * @param columnSchemaViews
 */
public record TableSchemaViewDto(String id, String name, IContainer<ColumnSchemaViewDto> columnSchemaViews) {
}
