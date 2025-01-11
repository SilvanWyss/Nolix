package ch.nolix.system.noderawdata.nodesearcher;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawdata.tabledefinition.FieldIndexCatalog;
import ch.nolix.systemapi.noderawdataapi.nodesearcherapi.IEntityNodeSearcher;

public final class EntityNodeSearcher implements IEntityNodeSearcher {

  @Override
  public IMutableNode<?> getStoredIdNodeFromEntityNode(final IMutableNode<?> entityNode) {
    return entityNode.getStoredChildNodeAt1BasedIndex(FieldIndexCatalog.ID_INDEX);
  }

  @Override
  public IMutableNode<?> getStoredSaveStampNodeFromEntityNode(final IMutableNode<?> entityNode) {
    return entityNode.getStoredChildNodeAt1BasedIndex(FieldIndexCatalog.SAVE_STAMP_INDEX);
  }
}
