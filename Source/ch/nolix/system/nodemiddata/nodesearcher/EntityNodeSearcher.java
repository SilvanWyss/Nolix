package ch.nolix.system.nodemiddata.nodesearcher;

import ch.nolix.coreapi.document.node.IMutableNode;
import ch.nolix.systemapi.nodemiddata.nodesearcher.IEntityNodeSearcher;
import ch.nolix.systemapi.nodemidschema.databasestructure.FieldIndexCatalog;

public final class EntityNodeSearcher implements IEntityNodeSearcher {

  @Override
  public String getIdFromEntityNode(final IMutableNode<?> entityNode) {

    final var idNode = getStoredIdNodeFromEntityNode(entityNode);

    return idNode.getHeader();
  }

  @Override
  public String getSaveStampFromEntityNode(final IMutableNode<?> entityNode) {

    final var saveStampNode = getStoredSaveStampNodeFromEntityNode(entityNode);

    return saveStampNode.getHeader();
  }

  @Override
  public IMutableNode<?> getStoredIdNodeFromEntityNode(final IMutableNode<?> entityNode) {
    return entityNode.getStoredChildNodeAtOneBasedIndex(FieldIndexCatalog.ID_INDEX);
  }

  @Override
  public IMutableNode<?> getStoredSaveStampNodeFromEntityNode(final IMutableNode<?> entityNode) {
    return entityNode.getStoredChildNodeAtOneBasedIndex(FieldIndexCatalog.SAVE_STAMP_INDEX);
  }
}
