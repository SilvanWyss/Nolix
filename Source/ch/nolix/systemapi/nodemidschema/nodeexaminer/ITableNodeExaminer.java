package ch.nolix.systemapi.nodemidschema.nodeexaminer;

import ch.nolix.coreapi.document.node.IMutableNode;

/**
 * @author Silvan Wyss
 * @version 2024-12-28
 */
public interface ITableNodeExaminer {

  /**
   * @param tableNode
   * @param columnName
   * @return true if the column of the given tableNode, that has the given
   *         columnName, is empty, false otherwise.
   */
  boolean columnOfTableNodeIsEmptyByColumnName(IMutableNode<?> tableNode, String columnName);
}
