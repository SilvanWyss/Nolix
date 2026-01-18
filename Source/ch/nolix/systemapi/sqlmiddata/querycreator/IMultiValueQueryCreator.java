/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlmiddata.querycreator;

import ch.nolix.coreapi.container.base.IContainer;

/**
 * @author Silvan Wyss
 */
public interface IMultiValueQueryCreator {
  String createQueryToCountMultiValueEntriesForGivenColumnAndValueIgnoringGivenEntities(
    String columnId,
    String value,
    IContainer<String> entitiesToIgnoreIds);

  String createQueryToLoadMultiValueEntries(String entityId, String multiValueColumnId);

  String createQueryToLoadOneOrNoneMultiValueEntryForGivenColumnAndValue(String columnId, String value);
}
