package ch.nolix.systemapi.middataapi.modelapi;

public record MultiReferenceEntryDto(
String tableName,
String entityId,
String multiReferenceColumnName,
String referencedEntityId,
String referencedEntityTableName) {
}
