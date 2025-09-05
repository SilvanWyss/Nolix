package ch.nolix.systemapi.sqlmiddata.querycreator;

import ch.nolix.coreapi.container.base.IContainer;

public interface IMultiReferenceQueryCreator {
  String createQueryToCountMultiReferenceEntriesForGivenColumnAndReferencedEntityIgnoringGivenEntities(
    String columnId,
    String referencedEntityId,
    IContainer<String> entitiesToIgnoreIds);

  String createQueryToLoadMultiReferenceEntries(String entityId, String multiReferenceColumnId);

  String createQueryToLoadOptionalFirstMultiReferenceEntry(
    String multiReferenceColumnId,
    String referencedEntityId);
}
