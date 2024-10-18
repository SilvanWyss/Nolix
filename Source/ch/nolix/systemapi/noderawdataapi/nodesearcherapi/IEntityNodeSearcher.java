package ch.nolix.systemapi.noderawdataapi.nodesearcherapi;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;

public interface IEntityNodeSearcher {

  IMutableNode<?> getStoredIdNodeFromEntityNode(IMutableNode<?> entityNode);

  IMutableNode<?> getStoredSaveStampNodeFromEntityNode(IMutableNode<?> entityNode);
}
