package ch.nolix.system.noderawdata.nodeeditor;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.systemapi.nodemidschemaapi.databasestructureapi.FieldIndexCatalog;
import ch.nolix.systemapi.nodemidschemaapi.databasestructureapi.NodeHeaderCatalog;
import ch.nolix.systemapi.noderawdataapi.nodeeditorapi.ITableNodeEditor;

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
      && a.getStoredChildNodeAt1BasedIndex(FieldIndexCatalog.ID_INDEX).hasHeader(id));
  }
}
