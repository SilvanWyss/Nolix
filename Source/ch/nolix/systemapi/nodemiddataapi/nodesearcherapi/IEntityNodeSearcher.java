package ch.nolix.systemapi.nodemiddataapi.nodesearcherapi;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;

public interface IEntityNodeSearcher {

  String getIdFromEntityNode(IMutableNode<?> entityNode);

  String getSaveStampFromEntityNode(IMutableNode<?> entityNode);

  IMutableNode<?> getStoredIdNodeFromEntityNode(IMutableNode<?> entityNode);

  IMutableNode<?> getStoredSaveStampNodeFromEntityNode(IMutableNode<?> entityNode);
}
