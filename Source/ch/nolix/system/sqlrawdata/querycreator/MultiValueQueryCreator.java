package ch.nolix.system.sqlrawdata.querycreator;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.sqlapi.syntaxapi.SpaceEnclosedSqlKeywordCatalogue;
import ch.nolix.system.sqlrawschema.multivalueentrytable.MultiValueEntryTableColumn;
import ch.nolix.systemapi.sqlrawdataapi.querycreatorapi.IMultiValueQueryCreator;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.MultiEntryTableType;

public final class MultiValueQueryCreator implements IMultiValueQueryCreator {

  @Override
  public String createQueryToCountMultiValueEntriesForGivenColumnAndValueIgnoringGivenEntities(
    final String columnId,
    final String value,
    final IContainer<String> entitiesToIgnoreIds) {
    return //
    "SELECT COUNT(*) FROM "
    + MultiEntryTableType.MULTI_VALUE_ENTRY.getQualifyingPrefix()
    + SpaceEnclosedSqlKeywordCatalogue.WHERE
    + MultiValueEntryTableColumn.MULTI_VALUE_COLUMN_ID.getQualifiedName()
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
    + " FROM "
    + MultiEntryTableType.MULTI_VALUE_ENTRY.getQualifiedName()
    + SpaceEnclosedSqlKeywordCatalogue.WHERE
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
    + MultiEntryTableType.MULTI_VALUE_ENTRY.getQualifiedName()
    + SpaceEnclosedSqlKeywordCatalogue.WHERE
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
