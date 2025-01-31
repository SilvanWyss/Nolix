package ch.nolix.systemapi.rawdataapi.schemaviewmodel;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

/**
 * @author Silvan Wyss
 * @version 2025-01-10
 * @param id
 * @param name
 * @param columnViews
 */
public record TableViewDto(String id, String name, IContainer<ColumnSchemaViewDto> columnViews) {
}
