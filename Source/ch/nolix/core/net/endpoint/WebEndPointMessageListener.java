package ch.nolix.core.net.endpoint;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.net.websocket.WebSocketCompleteMessage;
import ch.nolix.core.programcontrol.worker.BatchWorker;

final class WebEndPointMessageListener extends BatchWorker {

  private final WebSocketEndPoint parentWebEndPoint;

  private WebEndPointMessageListener(final WebSocketEndPoint parentWebEndPoint) {

    Validator.assertThat(parentWebEndPoint).thatIsNamed("parent WebEndPoint").isNotNull();

    this.parentWebEndPoint = parentWebEndPoint;

    start();
  }

  public static WebEndPointMessageListener forWebEndPoint(final WebSocketEndPoint webSocketEndPoint) {
    return new WebEndPointMessageListener(webSocketEndPoint);
  }

  @Override
  protected void runStep() {

    final var message = new WebSocketCompleteMessage(
      parentWebEndPoint::isOpen,
      parentWebEndPoint.getStoredInputStream(),
      parentWebEndPoint::receiveControlFrame);

    receiveMessage(message);
  }

  @Override
  protected boolean shouldRunNextStep() {
    return parentWebEndPoint.isOpen();
  }

  private void receiveMessage(final String message) {

    //A web socket can send frames that contain a payload of length 0 resp. an
    //empty message.
    if (!message.isEmpty()) {
      parentWebEndPoint.receiveRawMessageInBackground(message);
    }
  }

  private void receiveMessage(final WebSocketCompleteMessage message) {
    if (message.isComplete()) {
      receiveMessage(message.getMessage());
    }
  }
}
