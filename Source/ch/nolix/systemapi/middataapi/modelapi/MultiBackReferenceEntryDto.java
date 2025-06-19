package ch.nolix.systemapi.middataapi.modelapi;

public record MultiBackReferenceEntryDto(
String tableName,
String entityId,
String multiBackReferenceColumnName,
String backReferencedEntityId,
String backReferencedEntityTableName) {
}
