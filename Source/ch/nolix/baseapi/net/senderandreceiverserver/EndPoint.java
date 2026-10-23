/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.net.senderandreceiverserver;

import java.util.function.Consumer;

import ch.nolix.baseapi.net.endpoint.BaseEndPoint;

/**
 * @author Silvan Wyss
 */
public interface EndPoint extends BaseEndPoint {
  /**
   * @return true if the current {@link EndPoint} has a receiver, false otherwise
   */
  boolean hasReceiver();

  /**
   * Sends the given message.
   * 
   * @param message
   */
  void sendMessage(String message);

  /**
   * Sets the given receiver to the current {@link EndPoint}. The given receiver
   * receives the messages the current {@link EndPoint} receives.
   * 
   * @param receiver
   */
  void setReceiver(Consumer<String> receiver);
}
