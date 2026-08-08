/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.net.senderandreplierserver;

import java.util.function.UnaryOperator;

import ch.nolix.baseapi.net.endpoint.BaseEndPoint;

/**
 * @author Silvan Wyss
 */
public interface EndPoint extends BaseEndPoint {
  String getReplyForMessage(String message);

  boolean hasReplier();

  void setReplier(UnaryOperator<String> replier);
}
