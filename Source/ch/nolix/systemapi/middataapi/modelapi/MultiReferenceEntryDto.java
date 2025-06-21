package ch.nolix.systemapi.middataapi.modelapi;

public record MultiReferenceEntryDto(
String tableName,
String entityId,
String multiReferenceColumnId,
String referencedEntityId,
String referencedEntityTableId) {
}
