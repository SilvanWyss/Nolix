/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlmiddata.querycreator;

import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;

/**
 * @author Silvan Wyss
 */
public interface IMultiValueQueryCreator {
  String createQueryToCountMultiValueEntriesForGivenColumnAndValueIgnoringGivenEntities(
    String columnId,
    String value,
    IWellOrderContainer<String> entitiesToIgnoreIds);

  String createQueryToLoadMultiValueEntries(String entityId, String multiValueColumnId);

  String createQueryToLoadOneOrNoneMultiValueEntryForGivenColumnAndValue(String columnId, String value);
}
