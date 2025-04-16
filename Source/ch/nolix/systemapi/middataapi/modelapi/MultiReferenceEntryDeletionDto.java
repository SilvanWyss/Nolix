package ch.nolix.systemapi.middataapi.modelapi;

public record MultiReferenceEntryDeletionDto(
String tableName,
String entityid,
String multiReferenceColumnId,
String referencedEntityId) {
}
