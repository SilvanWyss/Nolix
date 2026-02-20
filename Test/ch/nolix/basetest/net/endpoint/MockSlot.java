/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.net.endpoint;

import ch.nolix.baseapi.net.endpoint.IEndPoint;
import ch.nolix.baseapi.net.endpoint.ISlot;

/**
 * @author Silvan Wyss
 */
public final class MockSlot implements ISlot {
  private String latestReceivedMessage;

  @Override
  public String getName() {
    return "slot";
  }

  public String getLatestReceivedMessage() {
    return latestReceivedMessage;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void takeBackendEndPoint(final IEndPoint endPoint) {
    endPoint.setReceiver(this::receiveMessage);
  }

  private void receiveMessage(final String message) {
    latestReceivedMessage = message;
  }
}
