package ch.nolix.systemapi.middataapi.modelapi;

public record MultiValueEntryDto(
String tableName,
String entityId,
String multiValueColumnId,
String value) {
}
