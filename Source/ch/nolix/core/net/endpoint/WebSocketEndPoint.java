package ch.nolix.core.net.endpoint;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.net.websocket.WebSocketFrame;
import ch.nolix.core.programcontrol.flowcontrol.GlobalFlowController;
import ch.nolix.coreapi.netapi.endpointprotocol.MessageType;
import ch.nolix.coreapi.netapi.netproperty.ConnectionType;
import ch.nolix.coreapi.netapi.netproperty.PeerType;
import ch.nolix.coreapi.netapi.securityproperty.SecurityMode;
import ch.nolix.coreapi.netapi.websocketapi.WebSocketFrameOpcodeMeaning;
import ch.nolix.coreapi.programcontrolapi.processproperty.TargetInfoState;

final class WebSocketEndPoint extends AbstractNetEndPoint {

  private static final int CONNECT_TIMEOUT_IN_MILLISECONDS = 500;

  private final PeerType peerType;

  private final Socket socket;

  private final InputStream socketInputStream;

  private final OutputStream socketOutputStream;

  public WebSocketEndPoint(
    final Socket socket,
    final InputStream socketInputStream,
    final OutputStream socketOutputStream) {

    super(TargetInfoState.WAITS_TO_TARGET_INFO);

    GlobalValidator.assertThat(socket).thatIsNamed(Socket.class).isNotNull();
    GlobalValidator.assertThat(socketInputStream).thatIsNamed("socket input stream").isNotNull();
    GlobalValidator.assertThat(socketOutputStream).thatIsNamed("socket output stream").isNotNull();

    peerType = PeerType.BACKEND;
    this.socket = socket;
    this.socketInputStream = socketInputStream;
    this.socketOutputStream = socketOutputStream;

    createMessageListenerAndWaitToTargetInfo();
  }

  @Override
  public PeerType getPeerType() {
    return peerType;
  }

  @Override
  public SecurityMode getSecurityMode() {
    return SecurityMode.NONE;
  }

  @Override
  public ConnectionType getConnectionType() {
    return ConnectionType.WEB_SOCKET;
  }

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

  @Override
  protected void sendRawMessage(final String rawMessage) {
    sendFrame(new WebSocketFrame(true, WebSocketFrameOpcodeMeaning.TEXT_FRAME, false, rawMessage));
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
        throw InvalidArgumentException.forArgumentNameAndArgument("control frame", controlFrame);
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

    GlobalFlowController.forMaxMilliseconds(CONNECT_TIMEOUT_IN_MILLISECONDS).waitUntil(this::hasTargetInfo);

    if (!hasTargetInfo()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "reached timeout while waiting to target");
    }
  }
}
