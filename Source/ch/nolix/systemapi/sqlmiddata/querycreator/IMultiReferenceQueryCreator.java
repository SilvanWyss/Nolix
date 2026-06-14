/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlmiddata.querycreator;

import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;

/**
 * @author Silvan Wyss
 */
public interface IMultiReferenceQueryCreator {
  String createQueryToCountMultiReferenceEntriesForGivenColumnAndReferencedEntityIgnoringGivenEntities(
    String columnId,
    String referencedEntityId,
    IWellOrderContainer<String> entitiesToIgnoreIds);

  String createQueryToLoadMultiReferenceEntries(String entityId, String multiReferenceColumnId);

  String createQueryToLoadOptionalFirstMultiReferenceEntry(
    String multiReferenceColumnId,
    String referencedEntityId);
}
