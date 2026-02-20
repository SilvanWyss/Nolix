/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.midschema.model;

import ch.nolix.baseapi.container.base.IContainer;
import ch.nolix.baseapi.datamodel.fieldproperty.DataType;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;

public record ColumnDto(
String id,
String name,
FieldType fieldType,
DataType dataType,
IContainer<String> referenceableTableIds,
IContainer<String> backReferenceableColumnIds) {
}
