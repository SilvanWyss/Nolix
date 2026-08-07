/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.messageserver;

import ch.nolix.base.net.websocket.WebSocketCompleteMessage;
import ch.nolix.base.programcontrol.worker.AbstractBatchWorker;
import ch.nolix.base.validation.validator.Validator;

final class WebEndPointMessageListener extends AbstractBatchWorker {
  private final WebSocketEndPoint parentWebEndPoint;

  private WebEndPointMessageListener(final WebSocketEndPoint parentWebEndPoint) {
    Validator.assertThat(parentWebEndPoint).thatIsNamed("parent WebEndPoint").isNotNull();

    this.parentWebEndPoint = parentWebEndPoint;

    start();
  }

  public static WebEndPointMessageListener forWebEndPoint(final WebSocketEndPoint webSocketEndPoint) {
    return new WebEndPointMessageListener(webSocketEndPoint);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void runStep() {
    final var message = //
    WebSocketCompleteMessage.fromIsOpenFunctionAndInputStreamAndControlFrameTaker(
      parentWebEndPoint::isOpen,
      parentWebEndPoint.getStoredInputStream(),
      parentWebEndPoint::receiveControlFrame);

    receiveMessage(message);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected boolean shouldRunNextStep() {
    return parentWebEndPoint.isOpen();
  }

  private void receiveMessage(final String message) {
    // A web socket can send frames that contain a payload of length 0 resp. an
    // empty message.
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
