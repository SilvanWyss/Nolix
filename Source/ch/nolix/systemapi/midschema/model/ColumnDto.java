/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.midschema.model;

import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;
import ch.nolix.baseapi.datamodel.fieldproperty.DataType;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;

public record ColumnDto(
String id,
String name,
FieldType fieldType,
DataType dataType,
IWellOrderContainer<String> referenceableTableIds,
IWellOrderContainer<String> backReferenceableColumnIds) {
}
