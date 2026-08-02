/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.websocket;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

import ch.nolix.base.datastructure.linkedlist.LinkedList;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.baseapi.generalstate.staterequest.CompletenessRequestable;

/**
 * @author Silvan Wyss
 */
public final class WebSocketCompleteMessage implements CompletenessRequestable {
  private boolean complete;

  private final LinkedList<Byte> message = LinkedList.createEmpty();

  private WebSocketCompleteMessage(
    final BooleanSupplier isOpenFunction,
    final InputStream inputStream,
    final Consumer<WebSocketFrame> controlFrameTaker) {
    while (isOpenFunction.getAsBoolean() && isIncomplete()) {
      final var frame = WebSocketFrame.fromInputStream(inputStream);

      addFrame(frame, controlFrameTaker);
    }
  }

  public static WebSocketCompleteMessage fromIsOpenFunctionAndInputStreamAndControlFrameTaker(
    final BooleanSupplier isOpenFunction,
    final InputStream inputStream,
    final Consumer<WebSocketFrame> controlFrameTaker) {
    return new WebSocketCompleteMessage(isOpenFunction, inputStream, controlFrameTaker);
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

  /**
   * {@inheritDoc}
   */
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
    final var frameType = frame.getFrameType();

    switch (frameType) {
      case CONTROL_FRAME:
        controlFrameTaker.accept(frame);
        break;
      case DATA_FRAME:
        addDataFrame(frame);
        break;
      default:
        throw InvalidArgumentException.forArgument(frameType);
    }
  }
}
