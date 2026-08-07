/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.net.level3server;

import ch.nolix.baseapi.net.endpoint.BaseEndPoint;

/**
 * @author Silvan Wyss
 */
public interface IEndPoint extends BaseEndPoint, ExecutorAndDataProvider {
  boolean hasReceivingDataProviderController();

  void setReceivingDataProviderController(ExecutorAndDataProvider receivingDataProviderController);
}
