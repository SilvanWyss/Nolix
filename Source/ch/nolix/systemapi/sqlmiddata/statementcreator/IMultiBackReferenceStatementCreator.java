package ch.nolix.systemapi.sqlmiddata.statementcreator;

public interface IMultiBackReferenceStatementCreator {

  String createStatementToDeleteMultiBackReferenceEntries(String entityId, String multiBackReferenceColumnId);

  String createStatementToDeleteMultiBackReferenceEntry(
    String entityId,
    String multiBackReferenceColumnId,
    String backReferencedEntityId);

  String createStatementToInsertMultiBackReferenceEntry(
    String entityId,
    String multiBackReferenceColumnId,
    String backReferencedEntityId,
    String backReferencedEntityTableId);
}
