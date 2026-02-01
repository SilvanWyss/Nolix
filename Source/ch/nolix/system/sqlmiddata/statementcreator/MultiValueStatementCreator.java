/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.sqlmiddata.statementcreator;

import ch.nolix.systemapi.sqlmiddata.statementcreator.IMultiValueStatementCreator;
import ch.nolix.systemapi.sqlmidschema.databasestructure.FixTable;
import ch.nolix.systemapi.sqlmidschema.databasestructure.MultiValueEntryColumn;

/**
 * @author Silvan Wyss
 */
public final class MultiValueStatementCreator implements IMultiValueStatementCreator {
  /**
   * {@inheritDoc}
   */
  @Override
  public String createStatementToDeleteMultiValueEntries(final String entityId, final String multiValueColumnId) {
    return //
    "DELETE FROM "
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

  /**
   * {@inheritDoc}
   */
  @Override
  public String createStatementToDeleteMultiValueEntry(
    final String entityId,
    final String multiValueColumnId,
    final String entry) {
    return //
    "DELETE FROM "
    + FixTable.MULTI_VALUE_ENTRY.getName()
    + " WHERE "
    + MultiValueEntryColumn.ENTITY_ID.getName()
    + " = '"
    + entityId
    + "' AND "
    + MultiValueEntryColumn.MULTI_VALUE_COLUMN_ID.getName()
    + " = '"
    + multiValueColumnId
    + "' AND "
    + MultiValueEntryColumn.VALUE.getName()
    + " = '"
    + entry
    + "';";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String createStatementToInsertMultiValueEntry(
    final String entityId,
    final String multiValueColumnId,
    final String entry) {
    return //
    "INSERT INTO "
    + FixTable.MULTI_VALUE_ENTRY.getName()
    + " ("
    + MultiValueEntryColumn.ENTITY_ID.getName()
    + ", "
    + MultiValueEntryColumn.MULTI_VALUE_COLUMN_ID.getName()
    + ", "
    + MultiValueEntryColumn.VALUE.getName()
    + ") VALUES ('"
    + entityId
    + "', '"
    + multiValueColumnId
    + "', '"
    + entry
    + "');";
  }
}
