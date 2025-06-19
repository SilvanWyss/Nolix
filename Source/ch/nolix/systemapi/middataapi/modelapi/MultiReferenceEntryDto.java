package ch.nolix.systemapi.middataapi.modelapi;

public record MultiReferenceEntryDto(
String tableName,
String entityid,
String multiReferenceColumnName,
String referencedEntityId,
String referencedEntityTableName) {
}
