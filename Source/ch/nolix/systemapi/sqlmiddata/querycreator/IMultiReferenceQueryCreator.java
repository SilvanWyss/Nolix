/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlmiddata.querycreator;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;

/**
 * @author Silvan Wyss
 */
public interface IMultiReferenceQueryCreator {
  /**
   * @param columnId
   * @param referencedEntityId
   * @param entitiesToIgnoreIds
   * @return a query to count the multi reference entries, that do not belong to
   *         one of the given entitiesToIgnoreIds, of the column with the given
   *         columnId that reference the entity with the given referencedEntityId
   */
  String createQueryToCountMultiReferenceEntriesForGivenColumnAndReferencedEntityIgnoringGivenEntities(
    String columnId,
    String referencedEntityId,
    ExtendedIterable<String> entitiesToIgnoreIds);

  /**
   * @param entityId
   * @param multiReferenceColumnId
   * @return a query to load the multi reference entries of the entity with the
   *         given entityId for the multi reference column with the given
   *         multiReferenceColumnId
   */
  String createQueryToLoadMultiReferenceEntries(String entityId, String multiReferenceColumnId);

  /**
   * @param multiReferenceColumnId
   * @param referencedEntityId
   * @return a query to load the first multi reference entry, if there is one, of
   *         the multi reference column with the given multiReferenceColumnId that
   *         references the entity with the given referencedEntityId
   */
  String createQueryToLoadOptionalFirstMultiReferenceEntry(
    String multiReferenceColumnId,
    String referencedEntityId);
}
