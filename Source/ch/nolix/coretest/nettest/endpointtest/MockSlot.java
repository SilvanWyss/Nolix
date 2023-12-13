//package declaration
package ch.nolix.coretest.nettest.endpointtest;

//own imports
import ch.nolix.coreapi.netapi.endpointapi.IEndPoint;
import ch.nolix.coreapi.netapi.endpointapi.ISlot;

//class
public final class MockSlot implements ISlot {

  //optional attribute
  private String latestReceivedMessage;

  //method
  @Override
  public String getName() {
    return "slot";
  }

  //method
  public String getLatestReceivedMessage() {
    return latestReceivedMessage;
  }

  //method
  @Override
  public void takeBackendEndPoint(final IEndPoint endPoint) {
    endPoint.setReceiver(this::receiveMessage);
  }

  //method
  private void receiveMessage(final String message) {
    latestReceivedMessage = message;
  }
}