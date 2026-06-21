/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.net.level3server;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.document.chainednode.IChainedNode;
import ch.nolix.baseapi.document.node.INode;

/**
 * @author Silvan Wyss
 */
public interface IDataProvider {
  INode<?> getDataForRequest(IChainedNode request);

  ExtendedIterable<? extends INode<?>> getDataForRequests(IChainedNode... requests);

  ExtendedIterable<? extends INode<?>> getDataForRequests(Iterable<? extends IChainedNode> requests);
}
