//package declaration
package ch.nolix.systemapi.rawdataapi.statementcreatorapi;

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
  String createStatementToInsertEntryIntoMultiReference(
    String entityId,
    String multiReferenceColumnId,
    String referencedEntityId);
}
