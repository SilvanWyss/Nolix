package ch.nolix.systemapi.midschema.model;

import ch.nolix.systemapi.midschema.fieldproperty.FieldType;

public record ColumnDto(String id, String name, FieldType fieldType, ContentModelDto contentModel) {
}
