package ch.nolix.systemapi.middataapi.modelapi;

public record MultiBackReferenceEntryDeletionDto(
String tableName,
String entityId,
String multiBackReferenceColumnId,
String backReferencedEntityId) {
}
