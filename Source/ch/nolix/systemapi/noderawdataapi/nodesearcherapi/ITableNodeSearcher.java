package ch.nolix.systemapi.noderawdataapi.nodesearcherapi;

import java.util.Optional;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;

public interface ITableNodeSearcher {

  Optional<? extends IMutableNode<?>> getOptionalStoredEntityNodeFromTableNode(
    IMutableNode<?> tableNode,
    String id);

  IMutableNode<?> getStoredEntityNodeFromTableNode(IMutableNode<?> tableNode, String id);

  IContainer<? extends IMutableNode<?>> getStoredEntityNodesFromTableNode(IMutableNode<?> tableNode);
}
