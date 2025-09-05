package ch.nolix.coreapi.net.endpoint2;

import java.util.function.UnaryOperator;

import ch.nolix.coreapi.net.baseendpoint.IBaseEndPoint;

public interface IEndPoint extends IBaseEndPoint {
  String getReplyForRequest(String request);

  boolean hasReplier();

  void setReplier(UnaryOperator<String> replier);
}
