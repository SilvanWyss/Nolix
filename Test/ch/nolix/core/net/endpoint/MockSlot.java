package ch.nolix.core.net.endpoint;

import ch.nolix.coreapi.netapi.endpointapi.IEndPoint;
import ch.nolix.coreapi.netapi.endpointapi.ISlot;

public final class MockSlot implements ISlot {

  private String latestReceivedMessage;

  @Override
  public String getName() {
    return "slot";
  }

  public String getLatestReceivedMessage() {
    return latestReceivedMessage;
  }

  @Override
  public void takeBackendEndPoint(final IEndPoint endPoint) {
    endPoint.setReceiver(this::receiveMessage);
  }

  private void receiveMessage(final String message) {
    latestReceivedMessage = message;
  }
}
