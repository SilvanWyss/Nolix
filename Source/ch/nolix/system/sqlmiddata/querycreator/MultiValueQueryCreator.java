/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.sqlmiddata.querycreator;

import ch.nolix.baseapi.container.base.IContainer;
import ch.nolix.systemapi.sqlmiddata.querycreator.IMultiValueQueryCreator;
import ch.nolix.systemapi.sqlmidschema.databasestructure.FixTable;
import ch.nolix.systemapi.sqlmidschema.databasestructure.MultiValueEntryColumn;

/**
 * @author Silvan Wyss
 */
public final class MultiValueQueryCreator implements IMultiValueQueryCreator {
  /**
   * {@inheritDoc}
   */
  @Override
  public String createQueryToCountMultiValueEntriesForGivenColumnAndValueIgnoringGivenEntities(
    final String columnId,
    final String value,
    final IContainer<String> entitiesToIgnoreIds) {
    return //
    "SELECT COUNT(*) FROM "
    + FixTable.MULTI_VALUE_ENTRY
    + " WHERE "
    + MultiValueEntryColumn.MULTI_VALUE_COLUMN_ID
    + " = '"
    + value
    + "' AND "
    + MultiValueEntryColumn.ENTITY_ID
    + " NOT IN ("
    + entitiesToIgnoreIds.toString()
    + ");";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String createQueryToLoadMultiValueEntries(final String entityId, final String multiValueColumnId) {
    return //
    "SELECT "
    + MultiValueEntryColumn.VALUE
    + " FROM "
    + FixTable.MULTI_VALUE_ENTRY
    + " WHERE "
    + MultiValueEntryColumn.ENTITY_ID
    + " = '"
    + entityId
    + "' AND "
    + MultiValueEntryColumn.MULTI_VALUE_COLUMN_ID
    + " = '"
    + multiValueColumnId
    + "';";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String createQueryToLoadOneOrNoneMultiValueEntryForGivenColumnAndValue(
    final String columnId,
    final String value) {
    return //
    "SELECT TOP 1 * FROM "
    + FixTable.MULTI_VALUE_ENTRY
    + " WHERE "
    + MultiValueEntryColumn.MULTI_VALUE_COLUMN_ID
    + " = '"
    + columnId
    + "' AND "
    + MultiValueEntryColumn.VALUE
    + " = '"
    + value
    + "';";
  }
}
