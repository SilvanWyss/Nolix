package ch.nolix.system.noderawdata.nodeeditor;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawdata.tabledefinition.FieldIndexCatalogue;
import ch.nolix.systemapi.noderawdataapi.nodeeditorapi.ITableNodeEditor;
import ch.nolix.systemapi.noderawschemaapi.databasestructureapi.NodeHeaderCatalogue;

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
      a -> a.hasHeader(NodeHeaderCatalogue.ENTITY)
      && a.getStoredChildNodeAt1BasedIndex(FieldIndexCatalogue.ID_INDEX).hasHeader(id));
  }
}
