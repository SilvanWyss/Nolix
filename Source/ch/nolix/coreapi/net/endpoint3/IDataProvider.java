package ch.nolix.coreapi.net.endpoint3;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.document.chainednode.IChainedNode;
import ch.nolix.coreapi.document.node.INode;

public interface IDataProvider {

  INode<?> getDataForRequest(IChainedNode request);

  IContainer<? extends INode<?>> getDataForRequests(IChainedNode request, IChainedNode... requests);

  IContainer<? extends INode<?>> getDataForRequests(Iterable<? extends IChainedNode> requests);
}
