package ch.nolix.system.sqlmiddata.querycreator;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.sqlapi.syntaxapi.SpaceEnclosedSqlKeywordCatalog;
import ch.nolix.systemapi.sqlmiddataapi.querycreatorapi.IMultiValueQueryCreator;
import ch.nolix.systemapi.sqlmidschemaapi.databasestructure.FixTableType;
import ch.nolix.systemapi.sqlmidschemaapi.databasestructure.MultiValueEntryTableColumn;

public final class MultiValueQueryCreator implements IMultiValueQueryCreator {

  @Override
  public String createQueryToCountMultiValueEntriesForGivenColumnAndValueIgnoringGivenEntities(
    final String columnId,
    final String value,
    final IContainer<String> entitiesToIgnoreIds) {
    return //
    "SELECT COUNT(*) FROM "
    + FixTableType.MULTI_VALUE_ENTRY.getName()
    + SpaceEnclosedSqlKeywordCatalog.WHERE
    + MultiValueEntryTableColumn.MULTI_VALUE_COLUMN_ID.getName()
    + " = '"
    + value
    + "' AND "
    + MultiValueEntryTableColumn.ENTITY_ID.getName()
    + " NOT IN ("
    + entitiesToIgnoreIds.toString()
    + ");";
  }

  @Override
  public String createQueryToLoadMultiValueEntries(final String entityId, final String multiValueColumnId) {
    return //
    "SELECT "
    + MultiValueEntryTableColumn.VALUE.getName()
    + SpaceEnclosedSqlKeywordCatalog.FROM
    + FixTableType.MULTI_VALUE_ENTRY.getName()
    + SpaceEnclosedSqlKeywordCatalog.WHERE
    + MultiValueEntryTableColumn.ENTITY_ID.getName()
    + " = '"
    + entityId
    + "' AND "
    + MultiValueEntryTableColumn.MULTI_VALUE_COLUMN_ID.getName()
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
    + FixTableType.MULTI_VALUE_ENTRY.getName()
    + SpaceEnclosedSqlKeywordCatalog.WHERE
    + MultiValueEntryTableColumn.MULTI_VALUE_COLUMN_ID.getName()
    + " = '"
    + columnId
    + "' AND "
    + MultiValueEntryTableColumn.VALUE.getName()
    + " = '"
    + value
    + "';";
  }
}
