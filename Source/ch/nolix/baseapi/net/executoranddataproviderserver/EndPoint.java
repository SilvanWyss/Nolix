/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.net.executoranddataproviderserver;

import ch.nolix.baseapi.net.endpoint.BaseEndPoint;

/**
 * @author Silvan Wyss
 */
public interface EndPoint extends BaseEndPoint, ExecutorAndDataProvider {
  /**
   * @return true if the current {@link EndPoint} has an executor and data
   *         provider, false otherwise
   */
  boolean hasExecutorAndDataProvider();

  /**
   * Sets the given executorAndDataProvider to the current {@link EndPoint}.
   * 
   * @param executorAndDataProvider
   */
  void setExecutorAndDataProvider(ExecutorAndDataProvider executorAndDataProvider);
}
