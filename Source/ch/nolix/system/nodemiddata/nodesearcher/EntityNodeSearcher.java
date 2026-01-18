package ch.nolix.system.nodemiddata.nodesearcher;

import ch.nolix.coreapi.document.node.IMutableNode;
import ch.nolix.systemapi.nodemiddata.nodesearcher.IEntityNodeSearcher;
import ch.nolix.systemapi.nodemidschema.databasestructure.FieldIndexCatalog;

/**
 * @author Silvan Wyss
 */
public final class EntityNodeSearcher implements IEntityNodeSearcher {
  @Override
  public String getIdFromEntityNode(final IMutableNode<?> entityNode) {
    final var idNode = getStoredIdNodeFromEntityNode(entityNode);

    return idNode.getHeader();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getSaveStampFromEntityNode(final IMutableNode<?> entityNode) {
    final var saveStampNode = getStoredSaveStampNodeFromEntityNode(entityNode);

    return saveStampNode.getHeader();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IMutableNode<?> getStoredIdNodeFromEntityNode(final IMutableNode<?> entityNode) {
    return entityNode.getStoredChildNodeAtOneBasedIndex(FieldIndexCatalog.ID_INDEX);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IMutableNode<?> getStoredSaveStampNodeFromEntityNode(final IMutableNode<?> entityNode) {
    return entityNode.getStoredChildNodeAtOneBasedIndex(FieldIndexCatalog.SAVE_STAMP_INDEX);
  }
}
