package ch.nolix.systemapi.rawdataapi.schemaviewmodel;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

/**
 * @author Silvan Wyss
 * @version 2025-01-31
 * @param name
 * @param tableSchemaViews
 */
public record DatabaseSchemaViewDto(String name, IContainer<TableSchemaViewDto> tableSchemaViews) {
}
