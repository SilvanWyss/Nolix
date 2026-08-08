/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.senderandreceiverserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import ch.nolix.base.errorcontrol.generalexception.WrapperException;
import ch.nolix.base.net.websocket.WebSocketFrame;
import ch.nolix.base.programcontrol.flowcontrol.FlowController;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.baseapi.net.netproperty.ConnectionType;
import ch.nolix.baseapi.net.netproperty.PeerType;
import ch.nolix.baseapi.net.netproperty.SecurityMode;
import ch.nolix.baseapi.net.senderandreceiverserver.TargetInfoState;
import ch.nolix.baseapi.net.senderandreceiverserverprotocol.MessageType;
import ch.nolix.baseapi.net.websocket.WebSocketFrameOpcodeMeaning;

final class WebSocketEndPoint extends AbstractNetEndPoint {
  private static final int CONNECT_TIMEOUT_IN_MILLISECONDS = 500;

  private final PeerType peerType;

  private final Socket socket;

  private final InputStream socketInputStream;

  private final OutputStream socketOutputStream;

  private WebSocketEndPoint(
    final Socket socket,
    final InputStream socketInputStream,
    final OutputStream socketOutputStream) {
    super(TargetInfoState.WAITING_TO_TARGET_INFO);

    Validator.assertThat(socket).thatIsNamed(Socket.class).isNotNull();
    Validator.assertThat(socketInputStream).thatIsNamed("socket input stream").isNotNull();
    Validator.assertThat(socketOutputStream).thatIsNamed("socket output stream").isNotNull();

    peerType = PeerType.BACKEND;
    this.socket = socket;
    this.socketInputStream = socketInputStream;
    this.socketOutputStream = socketOutputStream;

    createMessageListenerAndWaitToTargetInfo();
  }

  public static WebSocketEndPoint withSocketAndSocketInputStreamAndSocketOutputStream(
    final Socket socket,
    final InputStream socketInputStream,
    final OutputStream socketOutputStream) {
    return new WebSocketEndPoint(socket, socketInputStream, socketOutputStream);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public PeerType getPeerType() {
    return peerType;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public SecurityMode getSecurityMode() {
    return SecurityMode.NONE;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ConnectionType getConnectionType() {
    return ConnectionType.WEB_SOCKET;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void noteClose() {
    if (canWork()) {
      sendRawMessage(MessageType.CLOSE_MESSAGE.getPrefix());
    }

    try {
      socket.close();
    } catch (final IOException pIOException) {
      throw WrapperException.forError(pIOException);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void sendRawMessage(final String rawMessage) {
    final var webSocketFrame = //
    WebSocketFrame.withFinBitAndOpCodeAndMaskBitAndPayload(
      true,
      WebSocketFrameOpcodeMeaning.TEXT_FRAME,
      false,
      rawMessage);

    sendFrame(webSocketFrame);
  }

  InputStream getStoredInputStream() {
    return socketInputStream;
  }

  void receiveControlFrame(final WebSocketFrame controlFrame) {
    switch (controlFrame.getOpcodeMeaning()) {
      case PING:
        sendPongFrameForPingFrame(controlFrame);
        break;
      case CONNECTION_CLOSE:
        close();
        break;
      default:
        throw InvalidArgumentException.forArgumentAndArgumentName(controlFrame, "control frame");
    }
  }

  private boolean canWork() {
    return !socket.isClosed();
  }

  private void createMessageListenerAndWaitToTargetInfo() {
    WebEndPointMessageListener.forWebEndPoint(this);

    waitToTargetInfo();
  }

  private void sendBytes(final byte[] bytes) {
    assertIsOpen();

    try {
      socketOutputStream.write(bytes);
      socketOutputStream.flush();
    } catch (final IOException pIOException) {
      throw WrapperException.forError(pIOException);
    }
  }

  private void sendFrame(final WebSocketFrame frame) {
    sendBytes(frame.toBytes());
  }

  private void sendPongFrameForPingFrame(final WebSocketFrame pingFrame) {
    sendFrame(pingFrame.createPongFrame());
  }

  private void waitToTargetInfo() {
    FlowController.forMaxMilliseconds(CONNECT_TIMEOUT_IN_MILLISECONDS).waitUntil(this::hasTargetInfo);

    if (!hasTargetInfo()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "reached timeout while waiting to target");
    }
  }
}
