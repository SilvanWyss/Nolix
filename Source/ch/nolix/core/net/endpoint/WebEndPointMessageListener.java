//package declaration
package ch.nolix.core.net.endpoint;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.net.websocket.WebSocketCompleteMessage;
import ch.nolix.core.programcontrol.worker.BatchWorker;

//class
final class WebEndPointMessageListener extends BatchWorker {

  //attribute
  private final WebSocketEndPoint parentWebEndPoint;

  //constructor
  private WebEndPointMessageListener(final WebSocketEndPoint parentWebEndPoint) {

    GlobalValidator.assertThat(parentWebEndPoint).thatIsNamed("parent WebEndPoint").isNotNull();

    this.parentWebEndPoint = parentWebEndPoint;

    start();
  }

  //static method
  public static WebEndPointMessageListener forWebEndPoint(final WebSocketEndPoint webSocketEndPoint) {
    return new WebEndPointMessageListener(webSocketEndPoint);
  }

  //method
  @Override
  protected void runStep() {

    final var message = new WebSocketCompleteMessage(
      parentWebEndPoint::isOpen,
      parentWebEndPoint.getStoredInputStream(),
      parentWebEndPoint::receiveControlFrame);

    receiveMessage(message);
  }

  //method
  @Override
  protected boolean shouldRunNextStep() {
    return parentWebEndPoint.isOpen();
  }

  //method
  private void receiveMessage(final String message) {

    //A web socket can send frames that contain a payload of length 0 resp. an
    //empty message.
    if (!message.isEmpty()) {
      parentWebEndPoint.receiveRawMessageInBackground(message);
    }
  }

  //method
  private void receiveMessage(final WebSocketCompleteMessage message) {
    if (message.isComplete()) {
      receiveMessage(message.getMessage());
    }
  }
}
