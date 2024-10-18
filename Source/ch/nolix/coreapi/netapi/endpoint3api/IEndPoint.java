package ch.nolix.coreapi.netapi.endpoint3api;

import ch.nolix.coreapi.netapi.baseendpointapi.IBaseEndPoint;

public interface IEndPoint extends IBaseEndPoint, IDataProviderController {

  boolean hasReceivingDataProviderController();

  void setReceivingDataProviderController(IDataProviderController receivingDataProviderController);
}
