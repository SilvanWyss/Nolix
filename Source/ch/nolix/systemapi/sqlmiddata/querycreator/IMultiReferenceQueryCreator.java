/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlmiddata.querycreator;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;

/**
 * @author Silvan Wyss
 */
public interface IMultiReferenceQueryCreator {
  String createQueryToCountMultiReferenceEntriesForGivenColumnAndReferencedEntityIgnoringGivenEntities(
    String columnId,
    String referencedEntityId,
    ExtendedIterable<String> entitiesToIgnoreIds);

  String createQueryToLoadMultiReferenceEntries(String entityId, String multiReferenceColumnId);

  String createQueryToLoadOptionalFirstMultiReferenceEntry(
    String multiReferenceColumnId,
    String referencedEntityId);
}
