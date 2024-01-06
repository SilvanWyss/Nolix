//package declaration
package ch.nolix.core.net.endpoint;

//Java imports
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

//own imports
import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.net.constant.IPv6Catalogue;
import ch.nolix.core.net.constant.PortCatalogue;
import ch.nolix.coreapi.netapi.netproperty.ConnectionType;
import ch.nolix.coreapi.netapi.netproperty.PeerType;
import ch.nolix.coreapi.netapi.securityproperty.SecurityMode;
import ch.nolix.coreapi.programatomapi.variablenameapi.LowerCaseCatalogue;
import ch.nolix.coreapi.programcontrolapi.processproperty.TargetInfoState;

//class
/**
 * @author Silvan Wyss
 * @date 2017-05-06
 */
public final class SocketEndPoint extends NetEndPoint {

  //attribute
  private final PeerType peerType;

  //attribute
  private final Socket socket;

  //attribute
  private final InputStream socketInputStream;

  //attribute
  private final OutputStream socketOutputStream;

  //constructor
  /**
   * Creates a new {@link SocketEndPoint} that will connect to the main target on
   * the given port on the local machine.
   * 
   * @param port
   * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
   */
  public SocketEndPoint(final int port) {
    this(IPv6Catalogue.LOOP_BACK_ADDRESS, port);
  }

  //constructor
  /**
   * Creates a new {@link SocketEndPoint} that will connect to the given target on
   * the given port on the local machine.
   * 
   * @param port
   * @param target
   * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
   * @throws ArgumentIsNullException       if the given target is null.
   * @throws InvalidArgumentException      if the given target is blank.
   */
  public SocketEndPoint(final int port, final String target) {
    this(IPv6Catalogue.LOOP_BACK_ADDRESS, port, target);
  }

  //constructor
  /**
   * Creates a new {@link SocketEndPoint} that will connect to the main target on
   * the HTTP port (80) on the machine with the given ip.
   * 
   * @param ip
   */
  public SocketEndPoint(final String ip) {
    this(ip, PortCatalogue.HTTP);
  }

  //constructor
  /**
   * Creates a new {@link SocketEndPoint} that will connect to the main target on
   * the given port on the machine with the given ip.
   * 
   * @param ip
   * @param port
   * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
   */
  public SocketEndPoint(final String ip, final int port) {

    super(TargetInfoState.RECEIVED_TARGET_INFO);

    GlobalValidator
      .assertThat(port)
      .thatIsNamed(LowerCaseCatalogue.PORT)
      .isBetween(PortCatalogue.MIN_PORT, PortCatalogue.MAX_PORT);

    peerType = PeerType.FRONTEND;

    try {
      socket = new Socket(ip, port);
      socketInputStream = socket.getInputStream();
      socketOutputStream = socket.getOutputStream();
    } catch (final IOException pIOException) {
      throw WrapperException.forError(pIOException);
    }

    sendTargetMessage();
    new SocketEndPointMessageListener(this);
  }

  //constructor
  /**
   * Creates a new {@link SocketEndPoint} that will connect to the given target on
   * the given port on the machine with the given ip.
   * 
   * @param ip
   * @param port
   * @param target
   * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
   * @throws ArgumentIsNullException       if the given target is null.
   * @throws InvalidArgumentException      if the given target is blank.
   */
  public SocketEndPoint(final String ip, final int port, final String target) {

    super(target);

    GlobalValidator
      .assertThat(port)
      .thatIsNamed(LowerCaseCatalogue.PORT)
      .isBetween(PortCatalogue.MIN_PORT, PortCatalogue.MAX_PORT);

    peerType = PeerType.FRONTEND;

    try {
      socket = new Socket(ip, port);
      socketInputStream = socket.getInputStream();
      socketOutputStream = socket.getOutputStream();
    } catch (final IOException pIOException) {
      throw WrapperException.forError(pIOException);
    }

    sendTargetMessage();
    new SocketEndPointMessageListener(this);
  }

  //constructor
  /**
   * Creates a new {@link NetEndPoint} with the given socket. The given
   * socketInputStream and the given socketOutputStream belong to the given
   * socket.
   * 
   * @param socket
   * @param socketInputStream
   * @param socketOutputStream
   * @throws ArgumentIsNullException if the given socket is null.
   * @throws ArgumentIsNullException if the given socketInputStream is null.
   * @throws ArgumentIsNullException if the given socketOutputStream is null.
   */
  SocketEndPoint(
    final Socket socket,
    final InputStream socketInputStream,
    final OutputStream socketOutputStream) {

    super(TargetInfoState.RECEIVED_TARGET_INFO);

    GlobalValidator.assertThat(socket).thatIsNamed(Socket.class).isNotNull();
    GlobalValidator.assertThat(socketInputStream).thatIsNamed("socket input stream").isNotNull();
    GlobalValidator.assertThat(socketOutputStream).thatIsNamed("socket output stream").isNotNull();

    peerType = PeerType.BACKEND;
    this.socket = socket;
    this.socketInputStream = socketInputStream;
    this.socketOutputStream = socketOutputStream;

    new SocketEndPointMessageListener(this);
  }

  //constructor
  /**
   * Creates a new {@link NetEndPoint} with the given socket and target. The given
   * socketInputStream and the given socketOutputStream belong to the given
   * socket.
   * 
   * @param socket
   * @param socketInputStream
   * @param socketOutputStream
   * @param target
   * @throws ArgumentIsNullException  if the given socket is null.
   * @throws ArgumentIsNullException  if the given socketInputStream is null.
   * @throws ArgumentIsNullException  if the given socketOutputStream is null.
   * @throws ArgumentIsNullException  if the given target is null.
   * @throws InvalidArgumentException if the given target is blank.
   */
  SocketEndPoint(
    final Socket socket,
    final InputStream socketInputStream,
    final OutputStream socketOutputStream,
    final String target) {

    super(target);

    GlobalValidator.assertThat(socket).thatIsNamed(Socket.class).isNotNull();
    GlobalValidator.assertThat(socketInputStream).thatIsNamed("socket input stream").isNotNull();
    GlobalValidator.assertThat(socketOutputStream).thatIsNamed("socket output stream").isNotNull();

    peerType = PeerType.BACKEND;
    this.socket = socket;
    this.socketInputStream = socketInputStream;
    this.socketOutputStream = socketOutputStream;

    new SocketEndPointMessageListener(this);
  }

  //method
  @Override
  public PeerType getPeerType() {
    return peerType;
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public ConnectionType getConnectionType() {
    return ConnectionType.SOCKET;
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public SecurityMode getSecurityLevel() {
    return SecurityMode.NONE;
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public void noteClose() {
    if (canWork()) {
      try {
        sendRawMessage(NetEndPointProtocol.CLOSE_PREFIX);
        socket.close();
      } catch (final IOException pIOException) {
        throw WrapperException.forError(pIOException);
      }
    }
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  protected void sendRawMessage(final String rawMessage) {

    assertIsOpen();

    try {
      socketOutputStream.write((rawMessage + "\r\n").getBytes(StandardCharsets.UTF_8));
      socketOutputStream.flush();
    } catch (final IOException pIOException) {
      throw WrapperException.forError(pIOException);
    }
  }

  //method
  InputStream getStoredInputStream() {
    return socketInputStream;
  }

  //method
  private boolean canWork() {
    return !socket.isClosed();
  }
}
