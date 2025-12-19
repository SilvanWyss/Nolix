package ch.nolix.systemapi.nodemiddata.nodesearcher;

import ch.nolix.coreapi.document.node.IMutableNode;

/**
 * @author Silvan Wyss
 */
public interface IEntityNodeSearcher {
  String getIdFromEntityNode(IMutableNode<?> entityNode);

  String getSaveStampFromEntityNode(IMutableNode<?> entityNode);

  IMutableNode<?> getStoredIdNodeFromEntityNode(IMutableNode<?> entityNode);

  IMutableNode<?> getStoredSaveStampNodeFromEntityNode(IMutableNode<?> entityNode);
}
