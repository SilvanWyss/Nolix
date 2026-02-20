/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.net.endpoint3;

import ch.nolix.baseapi.net.baseendpoint.IBaseEndPoint;

/**
 * @author Silvan Wyss
 */
public interface IEndPoint extends IBaseEndPoint, IDataProviderController {
  boolean hasReceivingDataProviderController();

  void setReceivingDataProviderController(IDataProviderController receivingDataProviderController);
}
