/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.net.endpoint2;

import java.util.function.UnaryOperator;

import ch.nolix.baseapi.net.baseendpoint.IBaseEndPoint;

/**
 * @author Silvan Wyss
 */
public interface IEndPoint extends IBaseEndPoint {
  String getReplyForRequest(String request);

  boolean hasReplier();

  void setReplier(UnaryOperator<String> replier);
}
