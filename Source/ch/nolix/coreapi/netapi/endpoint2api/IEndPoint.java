//package declaration
package ch.nolix.coreapi.netapi.endpoint2api;

//Java imports
import java.util.function.UnaryOperator;

//own imports
import ch.nolix.coreapi.netapi.baseendpointapi.IBaseEndPoint;

//interface
public interface IEndPoint extends IBaseEndPoint {

  //method declaration
  String getReplyForRequest(String request);

  //method declaration
  boolean hasReplier();

  //method declaration
  void setReplier(UnaryOperator<String> replier);
}
