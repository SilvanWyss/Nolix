/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.net.endpoint3;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.document.chainednode.IChainedNode;
import ch.nolix.coreapi.document.node.INode;

/**
 * @author Silvan Wyss
 */
public interface IDataProvider {
  INode<?> getDataForRequest(IChainedNode request);

  IContainer<? extends INode<?>> getDataForRequests(IChainedNode... requests);

  IContainer<? extends INode<?>> getDataForRequests(Iterable<? extends IChainedNode> requests);
}
