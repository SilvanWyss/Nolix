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
  boolean hasReceiver();

  void sendMessage(String message);

  void setReceiver(Consumer<String> receiver);
}
