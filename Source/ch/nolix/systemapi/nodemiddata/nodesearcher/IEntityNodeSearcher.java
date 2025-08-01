package ch.nolix.systemapi.nodemiddata.nodesearcher;

import ch.nolix.coreapi.document.node.IMutableNode;

public interface IEntityNodeSearcher {

  String getIdFromEntityNode(IMutableNode<?> entityNode);

  String getSaveStampFromEntityNode(IMutableNode<?> entityNode);

  IMutableNode<?> getStoredIdNodeFromEntityNode(IMutableNode<?> entityNode);

  IMutableNode<?> getStoredSaveStampNodeFromEntityNode(IMutableNode<?> entityNode);
}
