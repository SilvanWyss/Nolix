package ch.nolix.systemapi.midschema.model;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;

public record ColumnDto(
String id,
String name,
FieldType fieldType,
DataType dataType,
IContainer<String> referenceableTableIds,
ContentModelDto contentModel) {
}
