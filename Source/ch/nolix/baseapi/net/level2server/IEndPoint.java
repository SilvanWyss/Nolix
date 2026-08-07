/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.net.level2server;

import java.util.function.UnaryOperator;

import ch.nolix.baseapi.net.baseendpoint.BaseEndPoint;

/**
 * @author Silvan Wyss
 */
public interface IEndPoint extends BaseEndPoint {
  String getReplyForRequest(String request);

  boolean hasReplier();

  void setReplier(UnaryOperator<String> replier);
}
