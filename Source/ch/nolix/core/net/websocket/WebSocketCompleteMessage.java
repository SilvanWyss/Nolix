package ch.nolix.core.net.websocket;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.stateapi.staterequestapi.CompletenessRequestable;

public final class WebSocketCompleteMessage implements CompletenessRequestable {

  private boolean complete;

  private final LinkedList<Byte> message = LinkedList.createEmpty();

  public WebSocketCompleteMessage(
    final BooleanSupplier isOpenFunction,
    final InputStream inputStream,
    final Consumer<WebSocketFrame> controlFrameTaker) {
    while (isOpenFunction.getAsBoolean() && isIncomplete()) {

      final var frame = new WebSocketFrame(inputStream);

      addFrame(frame, controlFrameTaker);
    }
  }

  public String getMessage() {
    return new String(getMessageAsByteArray(), StandardCharsets.UTF_8);
  }

  public byte[] getMessageAsByteArray() {

    final var byteArray = new byte[message.getCount()];
    var i = 0;
    for (final var b : message) {
      byteArray[i] = b.byteValue();
      i++;
    }

    return byteArray;
  }

  @Override
  public boolean isComplete() {
    return complete;
  }

  private void addDataFrame(final WebSocketFrame dataFrame) {

    for (final var b : dataFrame.getPayload()) {
      message.addAtEnd(b);
    }

    if (dataFrame.isFinalFragment()) {
      complete = true;
    }
  }

  private void addFrame(final WebSocketFrame frame, final Consumer<WebSocketFrame> controlFrameTaker) {
    switch (frame.getFrameType()) { //NOSONAR: A switch-statement allows to add probable additional cases.
      case CONTROL_FRAME:
        controlFrameTaker.accept(frame);
        break;
      case DATA_FRAME:
        addDataFrame(frame);
        break;
    }
  }
}
