package ch.nolix.systemapi.middataapi.modelapi;

public record MultiBackReferenceEntryDeletionDto(
String tableName,
String entityId,
String multiBackReferenceColumnName,
String backReferencedEntityId) {
}
