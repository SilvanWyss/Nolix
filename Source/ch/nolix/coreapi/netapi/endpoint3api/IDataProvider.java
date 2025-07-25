package ch.nolix.coreapi.netapi.endpoint3api;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.documentapi.chainednodeapi.IChainedNode;
import ch.nolix.coreapi.documentapi.nodeapi.INode;

public interface IDataProvider {

  INode<?> getDataForRequest(IChainedNode request);

  IContainer<? extends INode<?>> getDataForRequests(IChainedNode request, IChainedNode... requests);

  IContainer<? extends INode<?>> getDataForRequests(Iterable<? extends IChainedNode> requests);
}
