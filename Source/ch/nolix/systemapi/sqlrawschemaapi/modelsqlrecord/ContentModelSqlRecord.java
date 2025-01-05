package ch.nolix.systemapi.sqlrawschemaapi.modelsqlrecord;

/**
 * @author Silvan Wyss
 * @version 2025-01-05
 * @param contentTypeValue
 * @param dataTypeValue
 * @param referencedTableIdValue
 * @param backReferencedColumnIdValue
 */
public record ContentModelSqlRecord(
String contentTypeValue,
String dataTypeValue,
String referencedTableIdValue,
String backReferencedColumnIdValue) {
}
