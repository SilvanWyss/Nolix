package ch.nolix.system.sqlmiddata.statementcreator;

import ch.nolix.coreapi.sqlapi.syntaxapi.SpaceEnclosedSqlKeywordCatalog;
import ch.nolix.systemapi.sqlmiddataapi.statementcreatorapi.IMultiValueStatementCreator;
import ch.nolix.systemapi.sqlmidschemaapi.databasestructure.FixTable;
import ch.nolix.systemapi.sqlmidschemaapi.databasestructure.MultiValueEntryColumn;

public final class MultiValueStatementCreator implements IMultiValueStatementCreator {

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

  @Override
  public String createStatementToDeleteMultiValueEntry(
    final String entityId,
    final String multiValueColumnId,
    final String entry) {
    return //
    "DELETE FROM "
    + FixTable.MULTI_VALUE_ENTRY.getName()
    + SpaceEnclosedSqlKeywordCatalog.WHERE
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
