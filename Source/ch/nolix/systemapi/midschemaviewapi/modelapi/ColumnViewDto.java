package ch.nolix.systemapi.midschemaviewapi.modelapi;

import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.systemapi.midschemaapi.fieldproperty.ContentType;

/**
 * @author Silvan Wyss
 * @version 2025-01-10
 * @param id
 * @param name
 * @param oneBasedOrdinalIndex
 * @param contentType
 * @param dataType
 */
public record ColumnViewDto(
String id,
String name,
int oneBasedOrdinalIndex,
ContentType contentType,
DataType dataType) {
}
