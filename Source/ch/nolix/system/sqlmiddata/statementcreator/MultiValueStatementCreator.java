package ch.nolix.system.sqlmiddata.statementcreator;

import ch.nolix.coreapi.sqlapi.syntaxapi.SpaceEnclosedSqlKeywordCatalog;
import ch.nolix.systemapi.sqlmiddataapi.statementcreatorapi.IMultiValueStatementCreator;
import ch.nolix.systemapi.sqlmidschemaapi.databasestructure.FixTableType;
import ch.nolix.systemapi.sqlmidschemaapi.databasestructure.MultiValueEntryTableColumn;

public final class MultiValueStatementCreator implements IMultiValueStatementCreator {

  @Override
  public String createStatementToDeleteMultiValueEntries(final String entityId, final String multiValueColumnId) {
    return //
    "DELETE FROM "
    + FixTableType.MULTI_VALUE_ENTRY.getName()
    + " WHERE "
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
  public String createStatementToDeleteMultiValueEntry(
    final String entityId,
    final String multiValueColumnId,
    final String entry) {
    return //
    "DELETE FROM "
    + FixTableType.MULTI_VALUE_ENTRY.getName()
    + SpaceEnclosedSqlKeywordCatalog.WHERE
    + MultiValueEntryTableColumn.ENTITY_ID.getName()
    + " = '"
    + entityId
    + "' AND "
    + MultiValueEntryTableColumn.MULTI_VALUE_COLUMN_ID.getName()
    + " = '"
    + multiValueColumnId
    + "' AND "
    + MultiValueEntryTableColumn.VALUE.getName()
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
    + FixTableType.MULTI_VALUE_ENTRY.getName()
    + " ("
    + MultiValueEntryTableColumn.ENTITY_ID.getName()
    + ", "
    + MultiValueEntryTableColumn.MULTI_VALUE_COLUMN_ID.getName()
    + ", "
    + MultiValueEntryTableColumn.VALUE.getName()
    + ") VALUES ('"
    + entityId
    + "', '"
    + multiValueColumnId
    + "', '"
    + entry
    + "');";
  }
}
