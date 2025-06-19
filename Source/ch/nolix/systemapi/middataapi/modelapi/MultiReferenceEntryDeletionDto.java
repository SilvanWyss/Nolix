package ch.nolix.systemapi.middataapi.modelapi;

public record MultiReferenceEntryDeletionDto(
String tableName,
String entityId,
String multiReferenceColumnId,
String referencedEntityId) {
}
