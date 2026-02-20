/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.net.endpoint;

import java.util.function.Consumer;

import ch.nolix.baseapi.net.baseendpoint.IBaseEndPoint;

/**
 * @author Silvan Wyss
 */
public interface IEndPoint extends IBaseEndPoint {
  boolean hasReceiver();

  void sendMessage(String message);

  void setReceiver(Consumer<String> receiver);
}
