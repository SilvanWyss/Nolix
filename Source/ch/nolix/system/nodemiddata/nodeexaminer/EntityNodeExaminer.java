package ch.nolix.system.nodemiddata.nodeexaminer;

import ch.nolix.coreapi.document.node.IMutableNode;
import ch.nolix.system.nodemiddata.nodesearcher.EntityNodeSearcher;
import ch.nolix.systemapi.nodemiddata.nodeexaminer.IEntityNodeExaminer;
import ch.nolix.systemapi.nodemiddata.nodesearcher.IEntityNodeSearcher;

public final class EntityNodeExaminer implements IEntityNodeExaminer {
  private static final IEntityNodeSearcher ENTITY_NODE_SEARCHER = new EntityNodeSearcher();

  @Override
  public boolean entityNodeHasSaveStamp(final IMutableNode<?> entityNode, final String saveStamp) {
    return //
    entityNode != null
    && entityNodeHasSaveStampWhenIsNotNull(entityNode, saveStamp);
  }

  private boolean entityNodeHasSaveStampWhenIsNotNull(final IMutableNode<?> entityNode, final String saveStamp) {
    final var entityNodeSaveStamp = ENTITY_NODE_SEARCHER.getSaveStampFromEntityNode(entityNode);

    return entityNodeSaveStamp.equals(saveStamp);
  }
}
