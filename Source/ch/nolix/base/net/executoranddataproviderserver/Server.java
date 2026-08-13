/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.executoranddataproviderserver;

import ch.nolix.baseapi.net.executoranddataproviderserver.Slot;
import ch.nolix.baseapi.net.netproperty.SecurityMode;

/**
 * A {@link Server} is a {@link AbstractServer} that listens to
 * {@link NetEndPoint} on a specific port.
 * 
 * @author Silvan Wyss
 */
public final class Server extends AbstractServer {
  private final ch.nolix.base.net.senderandreplierserver.NetServer internalServer;

  /**
   * Creates a new {@link Server} that will listen to {@link NetEndPoint}s on
   * the given port.
   * 
   * @param port
   * @throws RuntimeException if the given port is not in [0, 65535]
   */
  private Server(final int port) {
    // Creates the internal net server of the current net server.
    internalServer = ch.nolix.base.net.senderandreplierserver.NetServer.forPort(port);

    // Creates a close dependency to the internal net server of the current net
    // server.
    createCloseDependencyTo(internalServer);
  }

  /**
   * Creates a new {@link Server} that will listen to {@link NetEndPoint}s on
   * the given port.
   * 
   * When a web browser connects to the {@link Server}, the {@link Server}
   * will send the given httpMessage and close the connection.
   * 
   * @param port
   * @param httpMessage
   * @throws RuntimeException if the given port is not in [0, 65535]
   * @throws RuntimeException if the given httpMessage is null
   * @throws RuntimeException if the given httpMessage is blank
   */
  private Server(final int port, final String httpMessage) {
    // Creates the internal net server of the current net server.
    internalServer = ch.nolix.base.net.senderandreplierserver.NetServer.forPortAndHttpMessage(port, httpMessage);

    // Creates a close dependency to the internal net server of the current net
    // server.
    createCloseDependencyTo(internalServer);
  }

  /**
   * @param port
   * @return a new {@link Server} that will listen to {@link NetEndPoint}s on
   *         the given port
   * @throws RuntimeException if the given port is not in [0, 65535]
   */
  public static Server forPort(final int port) {
    return new Server(port);
  }

  /**
   * @param port
   * @param httpMessage
   * @return a new {@link Server} that will listen to {@link NetEndPoint}s on
   *         the given port. When a web browser connects to the {@link Server},
   *         the {@link Server} will send the given httpMessage and close the
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
    return internalServer.getPort();
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
    internalServer.addDefaultSlot(SenderAndReplierSlot.withNameAndParentServer(defaultSlot.getName(), this));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void noteAddedSlot(final Slot slot) {
    internalServer.addSlot(SenderAndReplierSlot.withNameAndParentServer(slot.getName(), this));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void noteRemovedSlot(final Slot slot) {
    internalServer.removeSlotByName(slot.getName());
  }
}
