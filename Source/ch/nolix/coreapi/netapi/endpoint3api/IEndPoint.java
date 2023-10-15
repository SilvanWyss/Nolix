//package declaration
package ch.nolix.coreapi.netapi.endpoint3api;

//own imports
import ch.nolix.coreapi.netapi.baseendpointapi.IBaseEndPoint;

//interface
public interface IEndPoint extends IBaseEndPoint, IDataProviderController {

  // method declaration
  boolean hasReceivingDataProviderController();

  // method declaration
  void setReceivingDataProviderController(IDataProviderController receivingDataProviderController);
}
