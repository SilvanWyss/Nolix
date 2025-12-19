package ch.nolix.systemapi.sqlmiddata.querycreator;

/**
 * @author Silvan Wyss
 */
public interface IMultiBackReferenceQueryCreator {
  String createQueryToLoadMultiBackReferenceEntries(String entityId, String multiBackReferenceColumnId);

  String createQueryToLoadMultiBackReferenceEntries(
    String backReferencedTableName,
    String backReferencedSingleReferenceColumnName,
    String referencedEntityId);

  String createQueryToLoadMultiBackReferenceEntriesIds(String entityId, String multiBackReferenceColumnId);

  String createQueryToLoadOptionalFirstMultiBackReferenceEntry(
    String multiBackReferenceColumnId,
    String backReferencedEntityId);
}
