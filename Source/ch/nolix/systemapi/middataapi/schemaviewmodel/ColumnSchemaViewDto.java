package ch.nolix.systemapi.middataapi.schemaviewmodel;

import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.systemapi.objectschemaapi.fieldproperty.ContentType;

/**
 * @author Silvan Wyss
 * @version 2025-01-10
 * @param id
 * @param name
 * @param oneBasedOrdinalIndex
 * @param contentType
 * @param dataType
 */
public record ColumnSchemaViewDto(
String id,
String name,
int oneBasedOrdinalIndex,
ContentType contentType,
DataType dataType) {
}
