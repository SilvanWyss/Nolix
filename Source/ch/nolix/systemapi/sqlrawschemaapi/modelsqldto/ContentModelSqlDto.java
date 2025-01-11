package ch.nolix.systemapi.sqlrawschemaapi.modelsqldto;

/**
 * @author Silvan Wyss
 * @version 2025-01-05
 * @param contentType
 * @param dataType
 * @param referencedTableId
 * @param backReferencedColumnId
 */
public record ContentModelSqlDto(
String contentType,
String dataType,
String referencedTableId,
String backReferencedColumnId) {
}
