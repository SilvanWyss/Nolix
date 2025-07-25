package ch.nolix.systemapi.sqlmiddataapi.querycreatorapi;

import ch.nolix.coreapi.container.base.IContainer;

public interface IMultiValueQueryCreator {

  String createQueryToCountMultiValueEntriesForGivenColumnAndValueIgnoringGivenEntities(
    String columnId,
    String value,
    IContainer<String> entitiesToIgnoreIds);

  String createQueryToLoadMultiValueEntries(String entityId, String multiValueColumnId);

  String createQueryToLoadOneOrNoneMultiValueEntryForGivenColumnAndValue(String columnId, String value);
}
