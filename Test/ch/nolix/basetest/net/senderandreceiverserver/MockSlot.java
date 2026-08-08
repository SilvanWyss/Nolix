/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.net.senderandreceiverserver;

import ch.nolix.baseapi.net.senderandreceiverserver.EndPoint;
import ch.nolix.baseapi.net.senderandreceiverserver.Slot;

/**
 * @author Silvan Wyss
 */
public final class MockSlot implements Slot {
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
  public void takeBackendEndPoint(final EndPoint endPoint) {
    endPoint.setReceiver(this::receiveMessage);
  }

  private void receiveMessage(final String message) {
    latestReceivedMessage = message;
  }
}
