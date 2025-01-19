package ch.nolix.systemapi.rawschemaapi.modelapi;

/**
 * @author Silvan Wyss
 * @version 2025-01-19
 * @param referenceColumnId
 * @param referencedTableId
 */
public record TableReferenceDto(String referenceColumnId, String referencedTableId) {
}
