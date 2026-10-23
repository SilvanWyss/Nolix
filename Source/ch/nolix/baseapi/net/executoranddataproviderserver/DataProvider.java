/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.net.executoranddataproviderserver;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.document.chainednode.ChainedNode;
import ch.nolix.baseapi.document.node.Node;

/**
 * @author Silvan Wyss
 */
public interface DataProvider {
  /**
   * @param request
   * @return the data the current {@link DataProvider} provides for the given
   *         request
   */
  Node<?> getDataForRequest(ChainedNode request);

  /**
   * @param requests
   * @return the data the current {@link DataProvider} provides for the given
   *         requests
   */
  ExtendedIterable<? extends Node<?>> getDataForRequests(ChainedNode... requests);

  /**
   * @param requests
   * @return the data the current {@link DataProvider} provides for the given
   *         requests
   */
  ExtendedIterable<? extends Node<?>> getDataForRequests(Iterable<? extends ChainedNode> requests);
}
