/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlmiddata.statementcreator;

/**
 * @author Silvan Wyss
 */
public interface IMultiValueStatementCreator {
  String createStatementToDeleteMultiValueEntries(String entityId, String multiValueColumnName);

  String createStatementToDeleteMultiValueEntry(String entityId, String multiValueColumnId, String entry);

  String createStatementToInsertMultiValueEntry(String entityId, String multiValueColumnId, String entry);
}
