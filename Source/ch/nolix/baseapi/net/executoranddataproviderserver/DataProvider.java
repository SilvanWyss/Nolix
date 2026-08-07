/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.net.executoranddataproviderserver;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.document.chainednode.IChainedNode;
import ch.nolix.baseapi.document.node.Node;

/**
 * @author Silvan Wyss
 */
public interface DataProvider {
  Node<?> getDataForRequest(IChainedNode request);

  ExtendedIterable<? extends Node<?>> getDataForRequests(IChainedNode... requests);

  ExtendedIterable<? extends Node<?>> getDataForRequests(Iterable<? extends IChainedNode> requests);
}
