//package declaration
package ch.nolix.systemapi.rawdatabaseapi.sqlsyntaxapi;

//interface
public interface IMultiValueStatementCreator {

  // method declaration
  String createStatementToDeleteMultiValueEntries(String entityId, String multiValueColumnName);

  // method declaration
  String createStatementToDeleteMultiValueEntry(String entityId, String multiValueColumnId, String entry);

  // method declaration
  String createQueryToInsertEntryIntoMultiValue(String entityId, String multiValueColumnId, String entry);
}
