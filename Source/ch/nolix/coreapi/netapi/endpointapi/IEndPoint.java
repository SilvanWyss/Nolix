//package declaration
package ch.nolix.coreapi.netapi.endpointapi;

//own imports
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTaker;
import ch.nolix.coreapi.netapi.baseendpointapi.IBaseEndPoint;

//interface
public interface IEndPoint extends IBaseEndPoint {

  // method declaration
  boolean hasReceiver();

  // method declaration
  void sendMessage(String message);

  // method declaration
  void setReceiver(IElementTaker<String> receiver);
}
