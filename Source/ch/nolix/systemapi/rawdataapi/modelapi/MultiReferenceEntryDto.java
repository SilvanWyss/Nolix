package ch.nolix.systemapi.rawdataapi.modelapi;

public record MultiReferenceEntryDto(
String tableName,
String entityid,
String multiReferenceColumnId,
String referencedEntityId,
String referencedEntityTableId) {
}
