//package declaration
package ch.nolix.core.net.websocket;

//Java imports
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.functionapi.requestapi.CompletenessRequestable;

//class
public final class WebSocketCompleteMessage implements CompletenessRequestable {

  //attribute
  private boolean complete;

  //attribute
  private final LinkedList<Byte> message = new LinkedList<>();

  //constructor
  public WebSocketCompleteMessage(
      final BooleanSupplier isOpenFunction,
      final InputStream inputStream,
      final Consumer<WebSocketFrame> controlFrameTaker) {
    while (isOpenFunction.getAsBoolean() && isIncomplete()) {

      final var frame = new WebSocketFrame(inputStream);

      addFrame(frame, controlFrameTaker);
    }
  }

  //method
  public String getMessage() {
    return new String(getMessageAsByteArray(), StandardCharsets.UTF_8);
  }

  //method
  public byte[] getMessageAsByteArray() {

    final var byteArray = new byte[message.getElementCount()];
    var i = 0;
    for (final var b : message) {
      byteArray[i] = b.byteValue();
      i++;
    }

    return byteArray;
  }

  //method
  @Override
  public boolean isComplete() {
    return complete;
  }

  //method
  private void addFrame(final WebSocketFrame frame, final Consumer<WebSocketFrame> controlFrameTaker) {
    switch (frame.getFrameType()) { //NOSONAR: A switch-statement allows to add probable additional cases.
      case CONTROL_FRAME:
        controlFrameTaker.accept(frame);
        break;
      case DATA_FRAME:

        for (final var b : frame.getPayload()) {
          message.addAtEnd(b);
        }

        if (frame.isFinalFragment()) {
          complete = true;
        }

        break;
    }
  }
}
