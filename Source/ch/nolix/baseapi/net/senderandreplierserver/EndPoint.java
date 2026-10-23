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
  /**
   * @param message
   * @return the reply of the counterpart of the current {@link EndPoint} for the
   *         given message
   */
  String getReplyForMessage(String message);

  /**
   * @return true if the current {@link EndPoint} has a replier, false otherwise
   */
  boolean hasReplier();

  /**
   * Sets the given replier to the current {@link EndPoint}. The given replier
   * replies to the messages the current {@link EndPoint} receives.
   * 
   * @param replier
   */
  void setReplier(UnaryOperator<String> replier);
}
