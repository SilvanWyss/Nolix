package ch.nolix.system.noderawdata.nodevalidator;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawdata.nodeexaminer.TableNodeExaminer;
import ch.nolix.systemapi.noderawdataapi.nodeexaminerapi.ITableNodeExaminer;
import ch.nolix.systemapi.noderawdataapi.nodevalidatorapi.ITableNodeValidator;

/**
 * @author Silvan Wyss
 * @version 2025-02-23
 */
public final class TableNodeValidator implements ITableNodeValidator {

  private static final ITableNodeExaminer TABLE_NODE_EXAMINER = new TableNodeExaminer();

  /**
   * {@inheritDoc}n
   */
  @Override
  public void assertTableNodeContainsEntityWithId(final IMutableNode<?> tableNode, final String entityId) {
    if (!TABLE_NODE_EXAMINER.tableNodeContainsEntityNodeWithGivenId(tableNode, entityId)) {
      throw //
      InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
        "table node",
        tableNode,
        "does not contain an entity with the id '" + entityId + "'");
    }
  }

  /**
   * {@inheritDoc}n
   */
  @Override
  public void assertTableNodeDoesNotContainEntityWithId(final IMutableNode<?> tableNode, final String entityId) {
    if (TABLE_NODE_EXAMINER.tableNodeContainsEntityNodeWithGivenId(tableNode, entityId)) {
      throw //
      InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
        "table node",
        tableNode,
        "contains an entity with the id '" + entityId + "'");
    }
  }
}
