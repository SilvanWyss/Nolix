package ch.nolix.systemapi.sqlmidschema.modelsqldto;

/**
 * @author Silvan Wyss
 * @version 2025-01-05
 * @param fieldType
 * @param dataType
 * @param backReferencedColumnId
 */
public record ContentModelSqlDto(
String fieldType,
String dataType,
String backReferencedColumnId) {
}
