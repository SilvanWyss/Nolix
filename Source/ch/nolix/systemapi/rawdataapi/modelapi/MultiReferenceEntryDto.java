package ch.nolix.systemapi.rawdataapi.modelapi;

public record MultiReferenceEntryDto(
String entityid,
String multiReferenceColumnId,
String referencedEntityId,
String referencedEntityTableId) {
}
