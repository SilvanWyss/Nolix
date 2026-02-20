/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.endpoint;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import ch.nolix.base.errorcontrol.generalexception.WrapperException;
import ch.nolix.base.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.base.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.base.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.base.errorcontrol.validator.Validator;
import ch.nolix.baseapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.baseapi.net.endpointprotocol.MessageType;
import ch.nolix.baseapi.net.netconstant.IPv6Catalog;
import ch.nolix.baseapi.net.netconstant.PortCatalog;
import ch.nolix.baseapi.net.netproperty.ConnectionType;
import ch.nolix.baseapi.net.netproperty.PeerType;
import ch.nolix.baseapi.net.securityproperty.SecurityMode;
import ch.nolix.baseapi.programcontrol.processproperty.TargetInfoState;

/**
 * @author Silvan Wyss
 */
public final class SocketEndPoint extends AbstractNetEndPoint {
  private final PeerType peerType;

  private final Socket socket;

  private final InputStream socketInputStream;

  private final OutputStream socketOutputStream;

  /**
   * Creates a new {@link SocketEndPoint} that will connect to the main target on
   * the given port on the local machine.
   * 
   * @param port
   * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
   */
  public SocketEndPoint(final int port) {
    this(IPv6Catalog.LOOP_BACK_ADDRESS, port);
  }

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
    this(IPv6Catalog.LOOP_BACK_ADDRESS, port, target);
  }

  /**
   * Creates a new {@link SocketEndPoint} that will connect to the main target on
   * the HTTP port (80) on the machine with the given ip.
   * 
   * @param ip
   */
  public SocketEndPoint(final String ip) {
    this(ip, PortCatalog.HTTP);
  }

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

    Validator
      .assertThat(port)
      .thatIsNamed(LowerCaseVariableCatalog.PORT)
      .isBetween(PortCatalog.MIN_PORT, PortCatalog.MAX_PORT);

    peerType = PeerType.FRONTEND;

    try {
      socket = new Socket(ip, port);
      socketInputStream = socket.getInputStream();
      socketOutputStream = socket.getOutputStream();
    } catch (final IOException pIOException) {
      throw WrapperException.forError(pIOException);
    }

    sendTargetMessage();
    SocketEndPointMessageListener.forSocketEndPoint(this);
  }

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

    Validator
      .assertThat(port)
      .thatIsNamed(LowerCaseVariableCatalog.PORT)
      .isBetween(PortCatalog.MIN_PORT, PortCatalog.MAX_PORT);

    peerType = PeerType.FRONTEND;

    try {
      socket = new Socket(ip, port);
      socketInputStream = socket.getInputStream();
      socketOutputStream = socket.getOutputStream();
    } catch (final IOException pIOException) {
      throw WrapperException.forError(pIOException);
    }

    sendTargetMessage();
    SocketEndPointMessageListener.forSocketEndPoint(this);
  }

  /**
   * Creates a new {@link AbstractNetEndPoint} with the given socket. The given
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

    Validator.assertThat(socket).thatIsNamed(Socket.class).isNotNull();
    Validator.assertThat(socketInputStream).thatIsNamed("socket input stream").isNotNull();
    Validator.assertThat(socketOutputStream).thatIsNamed("socket output stream").isNotNull();

    peerType = PeerType.BACKEND;
    this.socket = socket;
    this.socketInputStream = socketInputStream;
    this.socketOutputStream = socketOutputStream;

    SocketEndPointMessageListener.forSocketEndPoint(this);
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

    Validator.assertThat(socket).thatIsNamed(Socket.class).isNotNull();
    Validator.assertThat(socketInputStream).thatIsNamed("socket input stream").isNotNull();
    Validator.assertThat(socketOutputStream).thatIsNamed("socket output stream").isNotNull();

    peerType = PeerType.BACKEND;
    this.socket = socket;
    this.socketInputStream = socketInputStream;
    this.socketOutputStream = socketOutputStream;

    SocketEndPointMessageListener.forSocketEndPoint(this);
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
