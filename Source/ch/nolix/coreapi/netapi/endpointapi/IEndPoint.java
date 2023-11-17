//package declaration
package ch.nolix.coreapi.netapi.endpointapi;

//Java imports
import java.util.function.Consumer;

//own imports
import ch.nolix.coreapi.netapi.baseendpointapi.IBaseEndPoint;

//interface
public interface IEndPoint extends IBaseEndPoint {

  //method declaration
  boolean hasReceiver();

  //method declaration
  void sendMessage(String message);

  //method declaration
  void setReceiver(Consumer<String> receiver);
}
