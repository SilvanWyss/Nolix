package ch.nolix.system.sqlmiddata.querycreator;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.sqlmiddataapi.querycreatorapi.IMultiValueQueryCreator;
import ch.nolix.systemapi.sqlmidschemaapi.databasestructure.FixTable;
import ch.nolix.systemapi.sqlmidschemaapi.databasestructure.MultiValueEntryColumn;

public final class MultiValueQueryCreator implements IMultiValueQueryCreator {

  @Override
  public String createQueryToCountMultiValueEntriesForGivenColumnAndValueIgnoringGivenEntities(
    final String columnId,
    final String value,
    final IContainer<String> entitiesToIgnoreIds) {
    return //
    "SELECT COUNT(*) FROM "
    + FixTable.MULTI_VALUE_ENTRY.getName()
    + " WHERE "
    + MultiValueEntryColumn.MULTI_VALUE_COLUMN_ID.getName()
    + " = '"
    + value
    + "' AND "
    + MultiValueEntryColumn.ENTITY_ID.getName()
    + " NOT IN ("
    + entitiesToIgnoreIds.toString()
    + ");";
  }

  @Override
  public String createQueryToLoadMultiValueEntries(final String entityId, final String multiValueColumnId) {
    return //
    "SELECT "
    + MultiValueEntryColumn.VALUE.getName()
    + " FROM "
    + FixTable.MULTI_VALUE_ENTRY.getName()
    + " WHERE "
    + MultiValueEntryColumn.ENTITY_ID.getName()
    + " = '"
    + entityId
    + "' AND "
    + MultiValueEntryColumn.MULTI_VALUE_COLUMN_ID.getName()
    + " = '"
    + multiValueColumnId
    + "';";
  }

  @Override
  public String createQueryToLoadOneOrNoneMultiValueEntryForGivenColumnAndValue(
    final String columnId,
    final String value) {
    return //
    "SELECT TOP 1 * FROM "
    + FixTable.MULTI_VALUE_ENTRY.getName()
    + " WHERE "
    + MultiValueEntryColumn.MULTI_VALUE_COLUMN_ID.getName()
    + " = '"
    + columnId
    + "' AND "
    + MultiValueEntryColumn.VALUE.getName()
    + " = '"
    + value
    + "';";
  }
}
