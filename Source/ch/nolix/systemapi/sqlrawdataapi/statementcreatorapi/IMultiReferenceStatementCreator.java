package ch.nolix.systemapi.sqlrawdataapi.statementcreatorapi;

public interface IMultiReferenceStatementCreator {

  String createStatementToDeleteMultiReferenceEntries(String entityId, String multiReferenceColumnId);

  String createStatementToDeleteMultiReferenceEntry(
    String entityId,
    String multiReferenceColumnId,
    String referencedEntityId);

  String createStatementToInsertMultiReferenceEntry(
    String entityId,
    String multiReferenceColumnId,
    String referencedEntityId);
}
