/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.net.executoranddataproviderserver;

import ch.nolix.baseapi.net.endpoint.BaseEndPoint;

/**
 * @author Silvan Wyss
 */
public interface EndPoint extends BaseEndPoint, ExecutorAndDataProvider {
  boolean hasExecutorAndDataProvider();

  void setExecutorAndDataProvider(ExecutorAndDataProvider executorAndDataProvider);
}
