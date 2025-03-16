package ch.nolix.system.noderawdata.nodesearcher;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.systemapi.nodemidschemaapi.databasestructureapi.FieldIndexCatalog;
import ch.nolix.systemapi.noderawdataapi.nodesearcherapi.IEntityNodeSearcher;

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
    return entityNode.getStoredChildNodeAt1BasedIndex(FieldIndexCatalog.ID_INDEX);
  }

  @Override
  public IMutableNode<?> getStoredSaveStampNodeFromEntityNode(final IMutableNode<?> entityNode) {
    return entityNode.getStoredChildNodeAt1BasedIndex(FieldIndexCatalog.SAVE_STAMP_INDEX);
  }
}
