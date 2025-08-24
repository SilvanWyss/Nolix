package ch.nolix.systemapi.middata.model;

//TODO: Add nullableAdditionalValue attribute to FieldDto
public record FieldDto(String columnName, Object nullableValue) {
}
