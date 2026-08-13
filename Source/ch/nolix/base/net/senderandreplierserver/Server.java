/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.senderandreplierserver;

import ch.nolix.baseapi.net.netproperty.SecurityMode;
import ch.nolix.baseapi.net.senderandreplierserver.Slot;

/**
 * A {@link Server} is a {@link AbstractServer} that listens to
 * {@link NetEndPoint} on a specific port.
 * 
 * @author Silvan Wyss
 */
public final class Server extends AbstractServer {
  private final ch.nolix.base.net.senderandreceiverserver.Server internalNetServer;

  /**
   * Creates a new {@link Server} that will listen to {@link NetEndPoint}s on the
   * given port.
   * 
   * @param port
   * @throws RuntimeException if the given port is not in [0, 65535]
   */
  private Server(final int port) {
    // Creates the internal net server of the current net server.
    internalNetServer = ch.nolix.base.net.senderandreceiverserver.Server.forPort(port);

    // Creates a close dependency to the internal net server of the current net
    // server.
    createCloseDependencyTo(internalNetServer);
  }

  /**
   * Creates a new {@link Server} that will listen to {@link NetEndPoint}s on the
   * given port.
   * 
   * When a web browser connects to the {@link Server}, the {@link Server} will
   * send the given httpMessage and close the connection.
   * 
   * @param port
   * @param httpMessage
   * @throws RuntimeException if the given port is not in [0, 65535]
   * @throws RuntimeException if the given httpMessage is null
   * @throws RuntimeException if the given httpMessage is blank
   */
  private Server(final int port, final String httpMessage) {
    internalNetServer = //
    ch.nolix.base.net.senderandreceiverserver.Server.forPortAndInitialHttpMessage(port, httpMessage);

    createCloseDependencyTo(internalNetServer);
  }

  /**
   * @param port
   * @return a new {@link Server} that will listen to {@link NetEndPoint}s on the
   *         given port
   * @throws RuntimeException if the given port is not in [0, 65535]
   */
  public static Server forPort(final int port) {
    return new Server(port);
  }

  /**
   * @param port
   * @param httpMessage
   * @return a new {@link Server} that will listen to {@link NetEndPoint}s on the
   *         given port. When a web browser connects to the {@link Server}, the
   *         {@link Server} will send the given httpMessage and close the
   *         connection
   * @throws RuntimeException if the given port is not in [0, 65535]
   * @throws RuntimeException if the given httpMessage is null
   * @throws RuntimeException if the given httpMessage is blank
   */
  public static Server forPortAndHttpMessage(final int port, final String httpMessage) {
    return new Server(port, httpMessage);
  }

  /**
   * @return the port of the current {@link Server}.
   */
  public int getPort() {
    return internalNetServer.getPort();
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
  protected void noteAddedDefaultSlot(final Slot defaultSlot) {
    internalNetServer.addDefaultSlot(SenderAndReceiverSlot.withNameAndParentServer(defaultSlot.getName(), this));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void noteAddedSlot(final Slot slot) {
    internalNetServer.addSlot(SenderAndReceiverSlot.withNameAndParentServer(slot.getName(), this));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void noteRemovedSlot(final Slot slot) {
    internalNetServer.removeSlotByName(slot.getName());
  }
}
