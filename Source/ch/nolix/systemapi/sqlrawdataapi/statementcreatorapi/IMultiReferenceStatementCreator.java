//package declaration
package ch.nolix.systemapi.sqlrawdataapi.statementcreatorapi;

//interface
public interface IMultiReferenceStatementCreator {

  //method declaration
  String createStatementToDeleteMultiReferenceEntries(String entityId, String multiReferenceColumnId);

  //method declaration
  String createStatementToDeleteMultiReferenceEntry(
    String entityId,
    String multiReferenceColumnId,
    String referencedEntityId);

  //method declaration
  String createStatementToInsertMultiReferenceEntry(
    String entityId,
    String multiReferenceColumnId,
    String referencedEntityId);
}
