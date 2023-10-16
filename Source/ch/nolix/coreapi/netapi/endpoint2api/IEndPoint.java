//package declaration
package ch.nolix.coreapi.netapi.endpoint2api;

import java.util.function.Function;

import ch.nolix.coreapi.netapi.baseendpointapi.IBaseEndPoint;

//interface
public interface IEndPoint extends IBaseEndPoint {

  //method declaration
  String getReplyForRequest(String request);

  //method declaration
  boolean hasReplier();

  //method declaration
  void setReplier(Function<String, String> replier);
}
