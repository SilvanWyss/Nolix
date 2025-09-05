package ch.nolix.system.nodemiddata.nodeeditor;

import ch.nolix.coreapi.document.node.IMutableNode;
import ch.nolix.systemapi.nodemiddata.nodeeditor.ITableNodeEditor;
import ch.nolix.systemapi.nodemidschema.databasestructure.FieldIndexCatalog;
import ch.nolix.systemapi.nodemidschema.databasestructure.NodeHeaderCatalog;

/**
 * @author Silvan Wyss
 * @version 2024-12-21
 */
public final class TableNodeEditor implements ITableNodeEditor {
  /**
   * {@inheritDoc}
   */
  @Override
  public IMutableNode<?> removeAndGetStoredEntityNodeById(final IMutableNode<?> tableNode, final String id) {
    return //
    tableNode.removeAndGetStoredFirstChildNodeThat(
      a -> a.hasHeader(NodeHeaderCatalog.ENTITY)
      && a.getStoredChildNodeAtOneBasedIndex(FieldIndexCatalog.ID_INDEX).hasHeader(id));
  }
}
