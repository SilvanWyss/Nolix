/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.net.endpoint;

import java.util.function.Consumer;

import ch.nolix.coreapi.net.baseendpoint.IBaseEndPoint;

/**
 * @author Silvan Wyss
 */
public interface IEndPoint extends IBaseEndPoint {
  boolean hasReceiver();

  void sendMessage(String message);

  void setReceiver(Consumer<String> receiver);
}
