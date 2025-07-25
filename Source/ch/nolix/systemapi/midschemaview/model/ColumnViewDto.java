package ch.nolix.systemapi.midschemaview.model;

import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.systemapi.midschema.fieldproperty.ContentType;

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
