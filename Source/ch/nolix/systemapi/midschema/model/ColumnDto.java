/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.midschema.model;

import ch.nolix.baseapi.datamodel.fieldproperty.DataType;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;

public record ColumnDto(
String id,
String name,
FieldType fieldType,
DataType dataType,
ExtendedIterable<String> referenceableTableIds,
ExtendedIterable<String> backReferenceableColumnIds) {
}
