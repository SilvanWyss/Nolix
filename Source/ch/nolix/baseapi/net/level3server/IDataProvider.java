/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.net.level3server;

import ch.nolix.baseapi.container.base.IContainer;
import ch.nolix.baseapi.document.chainednode.IChainedNode;
import ch.nolix.baseapi.document.node.INode;

/**
 * @author Silvan Wyss
 */
public interface IDataProvider {
  INode<?> getDataForRequest(IChainedNode request);

  IContainer<? extends INode<?>> getDataForRequests(IChainedNode... requests);

  IContainer<? extends INode<?>> getDataForRequests(Iterable<? extends IChainedNode> requests);
}
