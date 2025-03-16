package ch.nolix.systemapi.middataapi.modelapi;

public record MultiReferenceEntryDto(
String tableName,
String entityid,
String multiReferenceColumnId,
String referencedEntityId,
String referencedEntityTableId) {
}
