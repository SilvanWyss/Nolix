package ch.nolix.system.noderawdata.nodeeditor;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawdata.structure.SubNodeHeaderCatalogue;
import ch.nolix.system.noderawdata.tabledefinition.FieldIndexCatalogue;
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
      a -> a.hasHeader(SubNodeHeaderCatalogue.ENTITY)
      && a.getStoredChildNodeAt1BasedIndex(FieldIndexCatalogue.ID_INDEX).hasHeader(id));
  }
}
