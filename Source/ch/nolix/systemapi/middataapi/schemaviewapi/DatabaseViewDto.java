package ch.nolix.systemapi.middataapi.schemaviewapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

/**
 * @author Silvan Wyss
 * @version 2025-01-31
 * @param name
 * @param tableSchemaViews
 */
public record DatabaseViewDto(String name, IContainer<TableViewDto> tableSchemaViews) {
}
