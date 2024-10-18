package ch.nolix.systemapi.sqlrawdataapi.statementcreatorapi;

public interface IMultiValueStatementCreator {

  String createStatementToDeleteMultiValueEntries(String entityId, String multiValueColumnName);

  String createStatementToDeleteMultiValueEntry(String entityId, String multiValueColumnId, String entry);

  String createStatementToInsertMultiValueEntry(String entityId, String multiValueColumnId, String entry);
}
