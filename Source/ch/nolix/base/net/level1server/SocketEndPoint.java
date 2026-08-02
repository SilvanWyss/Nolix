/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.level1server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import ch.nolix.base.errorcontrol.generalexception.WrapperException;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.baseapi.net.endpointprotocol.MessageType;
import ch.nolix.baseapi.net.level1server.TargetInfoState;
import ch.nolix.baseapi.net.netcatalog.IPv6Catalog;
import ch.nolix.baseapi.net.netcatalog.PortCatalog;
import ch.nolix.baseapi.net.netproperty.ConnectionType;
import ch.nolix.baseapi.net.netproperty.PeerType;
import ch.nolix.baseapi.net.securityproperty.SecurityMode;

/**
 * @author Silvan Wyss
 */
public final class SocketEndPoint extends AbstractNetEndPoint {
  private final PeerType peerType;

  private final Socket socket;

  private final InputStream socketInputStream;

  private final OutputStream socketOutputStream;

  /**
   * Creates a new {@link SocketEndPoint} that will connect to the default slot on
   * the given port on the given host.
   * 
   * @param host
   * @param port
   * @throws RuntimeException if the given port is not in [0, 65535]
   */
  private SocketEndPoint(final String host, final int port) {
    super(TargetInfoState.RECEIVED_TARGET_INFO);

    Validator.assertThat(port).thatIsNamed(LowerCaseVariableNameCatalog.PORT).isPort();

    peerType = PeerType.FRONTEND;

    try {
      socket = new Socket(host, port);
      socketInputStream = socket.getInputStream();
      socketOutputStream = socket.getOutputStream();
    } catch (final IOException ioException) {
      throw WrapperException.forError(ioException);
    }

    sendTargetMessage();
    SocketEndPointMessageListener.forSocketEndPoint(this); // NOSONAR: The SocketEndPoint is fully constructed.
  }

  /**
   * Creates a new {@link SocketEndPoint} that will connect to the given slot on
   * the given port on the given host.
   * 
   * @param host
   * @param port
   * @param slot
   * @throws RuntimeException if the given port is not in [0, 65535]
   * @throws RuntimeException if the given slot is null
   * @throws RuntimeException if the given slot is blank
   */
  private SocketEndPoint(final String host, final int port, final String slot) {
    super(slot);

    Validator.assertThat(port).thatIsNamed(LowerCaseVariableNameCatalog.PORT).isPort();

    peerType = PeerType.FRONTEND;

    try {
      socket = new Socket(host, port);
      socketInputStream = socket.getInputStream();
      socketOutputStream = socket.getOutputStream();
    } catch (final IOException ioException) {
      throw WrapperException.forError(ioException);
    }

    sendTargetMessage();
    SocketEndPointMessageListener.forSocketEndPoint(this); // NOSONAR: The SocketEndPoint is fully constructed.
  }

  /**
   * Creates a new {@link AbstractNetEndPoint} with the given socket. The given
   * socketInputStream and the given socketOutputStream belong to the given
   * socket.
   * 
   * @param socket
   * @param socketInputStream
   * @param socketOutputStream
   * @throws RuntimeException if the given socket is null
   * @throws RuntimeException if the given socketInputStream is null
   * @throws RuntimeException if the given socketOutputStream is null
   */
  private SocketEndPoint(
    final Socket socket,
    final InputStream socketInputStream,
    final OutputStream socketOutputStream) {
    super(TargetInfoState.RECEIVED_TARGET_INFO);

    Validator.assertThat(socket).thatIsNamed(Socket.class).isNotNull();
    Validator.assertThat(socketInputStream).thatIsNamed("socket input stream").isNotNull();
    Validator.assertThat(socketOutputStream).thatIsNamed("socket output stream").isNotNull();

    peerType = PeerType.BACKEND;
    this.socket = socket;
    this.socketInputStream = socketInputStream;
    this.socketOutputStream = socketOutputStream;

    SocketEndPointMessageListener.forSocketEndPoint(this); // NOSONAR: The SocketEndPoint is fully constructed.
  }

  /**
   * Creates a new {@link AbstractNetEndPoint} with the given socket and target.
   * The given socketInputStream and the given socketOutputStream belong to the
   * given socket.
   * 
   * @param socket
   * @param socketInputStream
   * @param socketOutputStream
   * @param target
   * @throws RuntimeException if the given socket is null
   * @throws RuntimeException if the given socketInputStream is null
   * @throws RuntimeException if the given socketOutputStream is null
   * @throws RuntimeException if the given target is null
   * @throws RuntimeException if the given target is blank
   */
  private SocketEndPoint(
    final Socket socket,
    final InputStream socketInputStream,
    final OutputStream socketOutputStream,
    final String target) {
    super(target);

    Validator.assertThat(socket).thatIsNamed(Socket.class).isNotNull();
    Validator.assertThat(socketInputStream).thatIsNamed("socket input stream").isNotNull();
    Validator.assertThat(socketOutputStream).thatIsNamed("socket output stream").isNotNull();

    peerType = PeerType.BACKEND;
    this.socket = socket;
    this.socketInputStream = socketInputStream;
    this.socketOutputStream = socketOutputStream;

    SocketEndPointMessageListener.forSocketEndPoint(this); // NOSONAR: The SocketEndPoint is fully constructed.
  }

  /**
   * @param host
   * @param port
   * @return a new {@link SocketEndPoint} that will connect to the default slot on
   *         the given port on the given host
   * @throws RuntimeException if the given port is not in [0, 65535]
   */
  public static SocketEndPoint toGivenHostAndGivenPortAndDefaultSlot(final String host, final int port) {
    return new SocketEndPoint(host, port);
  }

  /**
   * @param host
   * @param port
   * @param slot
   * @return a new {@link SocketEndPoint} that will connect to the given slot on
   *         the given port on the given host
   * @throws RuntimeException if the given port is not in [0, 65535]
   * @throws RuntimeException if the given slot is null
   * @throws RuntimeException if the given slot is blank
   */
  public static SocketEndPoint toGivenHostAndGivenPortAndGivenSlot(
    final String host,
    final int port,
    final String slot) {
    return new SocketEndPoint(host, port, slot);
  }

  /**
   * @param host
   * @return a new {@link SocketEndPoint} that will connect to the default slot on
   *         the HTTP port on the given host.
   */
  public static SocketEndPoint toGivenHostAndHttpPortAndDefaultSlot(final String host) {
    return new SocketEndPoint(host, PortCatalog.HTTP);
  }

  /**
   * @param port
   * @return a new {@link SocketEndPoint} that will connect to the default slot on
   *         the given port on the locale machine
   * @throws RuntimeException if the given port is not in [0, 65535]
   */
  public static SocketEndPoint toLocaleMachineAndGivenPortAndDefaultSlot(final int port) {
    return new SocketEndPoint(IPv6Catalog.LOOP_BACK_ADDRESS, port);
  }

  /**
   * @param port
   * @return a new {@link SocketEndPoint} that will connect to the given slot on
   *         the given port on the local machine
   * @param slot
   * @throws RuntimeException if the given port is not in [0, 65535]
   * @throws RuntimeException if the given target slot null
   * @throws RuntimeException if the given target slot blank
   */
  public static SocketEndPoint toLocalMachineAndGivenPortAndGivenSlot(final int port, final String slot) {
    return new SocketEndPoint(IPv6Catalog.LOOP_BACK_ADDRESS, port, slot);
  }

  /**
   * @param socket
   * @param socketInputStream
   * @param socketOutputStream
   * @return a new {@link AbstractNetEndPoint} with the given socket. The given
   *         socketInputStream and the given socketOutputStream belong to the
   *         given socket
   * @throws RuntimeException if the given socket is null
   * @throws RuntimeException if the given socketInputStream is null
   * @throws RuntimeException if the given socketOutputStream is null
   */
  public static SocketEndPoint withSocketAndSocketInputStreamAndSocketOutputStream(
    final Socket socket,
    final InputStream socketInputStream,
    final OutputStream socketOutputStream) {
    return new SocketEndPoint(socket, socketInputStream, socketOutputStream);
  }

  /**
   * @param socket
   * @param socketInputStream
   * @param socketOutputStream
   * @param target
   * @return a new {@link AbstractNetEndPoint} with the given socket and target.
   *         The given socketInputStream and the given socketOutputStream belong
   *         to the given socket
   * @throws RuntimeException if the given socket is null
   * @throws RuntimeException if the given socketInputStream is null
   * @throws RuntimeException if the given socketOutputStream is null
   * @throws RuntimeException if the given target is null
   * @throws RuntimeException if the given target is blank
   */
  public static SocketEndPoint withSocketAndSocketInputStreamAndSocketOutputStreamAndTarget(
    final Socket socket,
    final InputStream socketInputStream,
    final OutputStream socketOutputStream,
    final String target) {
    return new SocketEndPoint(socket, socketInputStream, socketOutputStream, target);
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
  public ConnectionType getConnectionType() {
    return ConnectionType.SOCKET;
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
  public void noteClose() {
    if (canWork()) {
      try {
        sendRawMessage(MessageType.CLOSE_MESSAGE.getPrefix());
        socket.close();
      } catch (final IOException pIOException) {
        throw WrapperException.forError(pIOException);
      }
    }
  }

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

  InputStream getStoredInputStream() {
    return socketInputStream;
  }

  private boolean canWork() {
    return !socket.isClosed();
  }
}
