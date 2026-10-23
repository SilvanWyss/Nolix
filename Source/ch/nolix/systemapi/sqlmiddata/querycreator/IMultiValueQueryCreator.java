/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlmiddata.querycreator;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;

/**
 * @author Silvan Wyss
 */
public interface IMultiValueQueryCreator {
  /**
   * @param columnId
   * @param value
   * @param entitiesToIgnoreIds
   * @return a query to count the multi value entries, that do not belong to one
   *         of the given entitiesToIgnoreIds, of the column with the given
   *         columnId that have the given value
   */
  String createQueryToCountMultiValueEntriesForGivenColumnAndValueIgnoringGivenEntities(
    String columnId,
    String value,
    ExtendedIterable<String> entitiesToIgnoreIds);

  /**
   * @param entityId
   * @param multiValueColumnId
   * @return a query to load the multi value entries of the entity with the given
   *         entityId for the multi value column with the given multiValueColumnId
   */
  String createQueryToLoadMultiValueEntries(String entityId, String multiValueColumnId);

  /**
   * @param columnId
   * @param value
   * @return a query to load one or none multi value entry of the column with the
   *         given columnId that has the given value
   */
  String createQueryToLoadOneOrNoneMultiValueEntryForGivenColumnAndValue(String columnId, String value);
}
