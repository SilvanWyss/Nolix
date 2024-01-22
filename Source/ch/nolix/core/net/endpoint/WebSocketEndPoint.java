//package declaration
package ch.nolix.core.net.endpoint;

//Java imports
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

//own imports
import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.logging.GlobalLogger;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.net.websocket.WebSocketFrame;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.coreapi.netapi.netproperty.ConnectionType;
import ch.nolix.coreapi.netapi.netproperty.PeerType;
import ch.nolix.coreapi.netapi.securityproperty.SecurityMode;
import ch.nolix.coreapi.netapi.websocketapi.WebSocketFrameOpcodeMeaning;
import ch.nolix.coreapi.programcontrolapi.processproperty.TargetInfoState;

//class
final class WebSocketEndPoint extends NetEndPoint {

  //constant
  private static final int CONNECT_TIMEOUT_IN_MILLISECONDS = 500;

  //attribute
  private final PeerType peerType;

  //attribute
  private final Socket socket;

  //attribute
  private final InputStream socketInputStream;

  //attribute
  private final OutputStream socketOutputStream;

  //constructor
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

    WebEndPointMessageListener.forWebEndPoint(this);

    GlobalLogger.logInfo("The current WebEndPoint waits to target info.");
    waitToTargetInfo();

    GlobalLogger.logInfo("Created a WebEndPoint.");
  }

  //method
  @Override
  public PeerType getPeerType() {
    return peerType;
  }

  //method
  @Override
  public SecurityMode getSecurityLevel() {
    return SecurityMode.NONE;
  }

  //method
  @Override
  public ConnectionType getConnectionType() {
    return ConnectionType.WEB_SOCKET;
  }

  //method
  @Override
  public void noteClose() {

    if (canWork()) {
      sendRawMessage(NetEndPointProtocol.CLOSE_PREFIX);
    }

    try {
      socket.close();
    } catch (final IOException pIOException) {
      throw WrapperException.forError(pIOException);
    }
  }

  //method
  @Override
  protected void sendRawMessage(final String rawMessage) {
    sendFrame(new WebSocketFrame(true, WebSocketFrameOpcodeMeaning.TEXT_FRAME, false, rawMessage));
  }

  //method
  InputStream getStoredInputStream() {
    return socketInputStream;
  }

  //method
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

  //method
  private boolean canWork() {
    return !socket.isClosed();
  }

  //method
  private void sendBytes(final byte[] bytes) {

    assertIsOpen();

    try {
      socketOutputStream.write(bytes);
      socketOutputStream.flush();
    } catch (final IOException pIOException) {
      throw WrapperException.forError(pIOException);
    }
  }

  //method
  private void sendFrame(final WebSocketFrame frame) {
    sendBytes(frame.toBytes());
  }

  //method
  private void sendPongFrameForPingFrame(final WebSocketFrame pingFrame) {
    sendFrame(pingFrame.createPongFrame());
  }

  //method
  private void waitToTargetInfo() {

    GlobalSequencer.forMaxMilliseconds(CONNECT_TIMEOUT_IN_MILLISECONDS).waitUntil(this::hasTargetInfo);

    if (!hasTargetInfo()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "reached timeout while waiting to target");
    }
  }
}
