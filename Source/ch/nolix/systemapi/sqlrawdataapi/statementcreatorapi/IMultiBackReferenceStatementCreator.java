//package declaration
package ch.nolix.systemapi.sqlrawdataapi.statementcreatorapi;

//interface
public interface IMultiBackReferenceStatementCreator {

  //method declaration
  String createStatementToDeleteMultiBackReferenceEntries(String entityId, String multiBackReferenceColumnId);

  //method declaration
  String createStatementToDeleteMultiBackReferenceEntry(
    String entityId,
    String multiBackReferenceColumnId,
    String backReferencedEntityId);

  //method declaration
  String createStatementToInsertMultiBackReferenceEntry(
    String entityId,
    String multiBackReferenceColumnId,
    String backReferencedEntityId);
}
