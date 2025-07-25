package ch.nolix.systemapi.nodemiddataapi.nodesearcherapi;

import java.util.Optional;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.document.node.IMutableNode;

public interface ITableNodeSearcher {

  Optional<? extends IMutableNode<?>> getOptionalStoredEntityNodeFromTableNode(
    IMutableNode<?> tableNode,
    String id);

  IContainer<? extends IMutableNode<?>> getStoredColumnNodesFromTableNode(IMutableNode<?> tableNode);

  IMutableNode<?> getStoredEntityNodeFromTableNode(IMutableNode<?> tableNode, String id);

  IContainer<? extends IMutableNode<?>> getStoredEntityNodesFromTableNode(IMutableNode<?> tableNode);

  IMutableNode<?> getStoredIdNodeFromTableNode(IMutableNode<?> tableNode);

  IMutableNode<?> getStoredNameNodeFromTableNode(IMutableNode<?> tableNode);

  String getTableIdFromTableNode(IMutableNode<?> tableNode);

  String getTableNameFromTableNode(IMutableNode<?> tableNode);
}
