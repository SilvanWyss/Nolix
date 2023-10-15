//package declaration
package ch.nolix.coreapi.netapi.endpoint3api;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.chainednodeapi.IChainedNode;
import ch.nolix.coreapi.documentapi.nodeapi.INode;

//interface
public interface IDataProvider {

  // method declaration
  INode<?> getDataForRequest(IChainedNode request);

  // method declaration
  IContainer<? extends INode<?>> getDataForRequests(IChainedNode request, IChainedNode... requests);

  // method declaration
  IContainer<? extends INode<?>> getDataForRequests(Iterable<? extends IChainedNode> requests);
}
