/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlmiddata.statementcreator;

/**
 * @author Silvan Wyss
 */
public interface IMultiValueStatementCreator {
  /**
   * @param entityId
   * @param multiValueColumnName
   * @return a statement to delete the multi value entries of the entity with the
   *         given entityId for the multi value column with the given
   *         multiValueColumnName
   */
  String createStatementToDeleteMultiValueEntries(String entityId, String multiValueColumnName);

  /**
   * @param entityId
   * @param multiValueColumnId
   * @param entry
   * @return a statement to delete the given entry of the entity with the given
   *         entityId for the multi value column with the given multiValueColumnId
   */
  String createStatementToDeleteMultiValueEntry(String entityId, String multiValueColumnId, String entry);

  /**
   * @param entityId
   * @param multiValueColumnId
   * @param entry
   * @return a statement to insert the given entry for the entity with the given
   *         entityId and the multi value column with the given multiValueColumnId
   */
  String createStatementToInsertMultiValueEntry(String entityId, String multiValueColumnId, String entry);
}
