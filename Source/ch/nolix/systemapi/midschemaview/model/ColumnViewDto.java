package ch.nolix.systemapi.midschemaview.model;

import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;

/**
 * @author Silvan Wyss
 * @param id
 * @param name
 * @param oneBasedOrdinalIndex
 * @param fieldType
 * @param dataType
 */
public record ColumnViewDto(
String id,
String name,
int oneBasedOrdinalIndex,
FieldType fieldType,
DataType dataType) {
}
