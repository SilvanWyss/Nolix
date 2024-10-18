package ch.nolix.systemapi.sqlrawdataapi.statementcreatorapi;

public interface IMultiBackReferenceStatementCreator {

  String createStatementToDeleteMultiBackReferenceEntries(String entityId, String multiBackReferenceColumnId);

  String createStatementToDeleteMultiBackReferenceEntry(
    String entityId,
    String multiBackReferenceColumnId,
    String backReferencedEntityId);

  String createStatementToInsertMultiBackReferenceEntry(
    String entityId,
    String multiBackReferenceColumnId,
    String backReferencedEntityId);
}
