package ch.nolix.coreapi.net.endpoint3;

import ch.nolix.coreapi.net.baseendpoint.IBaseEndPoint;

public interface IEndPoint extends IBaseEndPoint, IDataProviderController {
  boolean hasReceivingDataProviderController();

  void setReceivingDataProviderController(IDataProviderController receivingDataProviderController);
}
