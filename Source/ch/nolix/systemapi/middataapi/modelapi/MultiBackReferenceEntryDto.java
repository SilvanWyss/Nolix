package ch.nolix.systemapi.middataapi.modelapi;

public record MultiBackReferenceEntryDto(
String tableName,
String entityId,
String multiBackReferenceColumnId,
String backReferencedEntityId,
String backReferencedEntityTableId) {
}
