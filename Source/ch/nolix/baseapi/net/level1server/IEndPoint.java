/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.net.level1server;

import java.util.function.Consumer;

import ch.nolix.baseapi.net.baseendpoint.BaseEndPoint;

/**
 * @author Silvan Wyss
 */
public interface IEndPoint extends BaseEndPoint {
  boolean hasReceiver();

  void sendMessage(String message);

  void setReceiver(Consumer<String> receiver);
}
