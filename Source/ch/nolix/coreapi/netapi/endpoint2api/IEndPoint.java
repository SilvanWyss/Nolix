//package declaration
package ch.nolix.coreapi.netapi.endpoint2api;

//own imports
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTakerElementGetter;
import ch.nolix.coreapi.netapi.baseendpointapi.IBaseEndPoint;

//interface
public interface IEndPoint extends IBaseEndPoint {

  // method declaration
  String getReplyForRequest(String request);

  // method declaration
  boolean hasReplier();

  // method declaration
  void setReplier(IElementTakerElementGetter<String, String> replier);
}
