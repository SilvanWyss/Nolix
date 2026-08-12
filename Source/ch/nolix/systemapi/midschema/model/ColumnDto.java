/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.midschema.model;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.systemapi.database.databaseproperty.DataType;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;

public record ColumnDto(
String id,
String name,
FieldType fieldType,
DataType dataType,
ExtendedIterable<String> referenceableTableIds,
ExtendedIterable<String> backReferenceableColumnIds) {
}
