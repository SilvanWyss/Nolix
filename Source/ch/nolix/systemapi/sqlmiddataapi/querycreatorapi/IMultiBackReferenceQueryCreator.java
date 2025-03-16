package ch.nolix.systemapi.sqlmiddataapi.querycreatorapi;

public interface IMultiBackReferenceQueryCreator {

  String createQueryToLoadMultiBackReferenceEntries(String entityId, String multiBackReferenceColumnId);

  String createQueryToLoadOptionalFirstMultiBackReferenceEntry(
    String multiBackReferenceColumnId,
    String backReferencedEntityId);
}
