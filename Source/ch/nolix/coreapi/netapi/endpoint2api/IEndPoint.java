package ch.nolix.coreapi.netapi.endpoint2api;

import java.util.function.UnaryOperator;

import ch.nolix.coreapi.netapi.baseendpointapi.IBaseEndPoint;

public interface IEndPoint extends IBaseEndPoint {

  String getReplyForRequest(String request);

  boolean hasReplier();

  void setReplier(UnaryOperator<String> replier);
}
