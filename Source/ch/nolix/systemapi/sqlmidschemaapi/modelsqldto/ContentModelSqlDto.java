package ch.nolix.systemapi.sqlmidschemaapi.modelsqldto;

/**
 * @author Silvan Wyss
 * @version 2025-01-05
 * @param contentType
 * @param dataType
 * @param backReferencedColumnId
 */
public record ContentModelSqlDto(
String contentType,
String dataType,
String backReferencedColumnId) {
}
