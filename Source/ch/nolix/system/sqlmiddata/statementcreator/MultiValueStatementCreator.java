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
  public String createStatementToDeleteMultiValueEntry(
    final String entityId,
    final String multiValueColumnId,
    final String entry) {
    return //
    "DELETE FROM "
    + FixTable.MULTI_VALUE_ENTRY
    + " WHERE "
    + MultiValueEntryColumn.ENTITY_ID
    + " = '"
    + entityId
    + "' AND "
    + MultiValueEntryColumn.MULTI_VALUE_COLUMN_ID
    + " = '"
    + multiValueColumnId
    + "' AND "
    + MultiValueEntryColumn.VALUE
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
    + FixTable.MULTI_VALUE_ENTRY
    + " ("
    + MultiValueEntryColumn.ENTITY_ID
    + ", "
    + MultiValueEntryColumn.MULTI_VALUE_COLUMN_ID
    + ", "
    + MultiValueEntryColumn.VALUE
    + ") VALUES ('"
    + entityId
    + "', '"
    + multiValueColumnId
    + "', '"
    + entry
    + "');";
  }
}
