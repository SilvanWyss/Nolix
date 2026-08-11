/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.clientserver;

import ch.nolix.base.environment.localcomputer.LocalComputer;
import ch.nolix.base.net.target.ServerTarget;
import ch.nolix.baseapi.net.clientserver.Application;
import ch.nolix.baseapi.net.netattribute.HostHolder;
import ch.nolix.baseapi.net.netattribute.PortHolder;
import ch.nolix.baseapi.net.netcatalog.PortCatalog;
import ch.nolix.baseapi.net.netproperty.SecurityMode;
import ch.nolix.baseapi.net.target.IServerTarget;

/**
 * A {@link Server} is a {@link AbstractServer} that listens to net
 * {@link AbstractClient}s on a specific port.
 * 
 * @author Silvan Wyss
 */
public final class Server extends AbstractServer<Server> implements HostHolder, PortHolder {
  private static final SecurityMode SECURITY_MODE_FOR_CONNECTIONS = SecurityMode.NONE;

  private ch.nolix.base.net.executoranddataproviderserver.NetServer internalServer;

  /**
   * Creates a new {@link Server} that will listen to net {@link AbstractClient}s
   * on the given port.
   * 
   * @param port
   * @throws RuntimeException if the given port is not in [0, 65535]
   */
  private Server(final int port) {
    // Creates the internalServer of the current Server.
    internalServer = ch.nolix.base.net.executoranddataproviderserver.NetServer.forPortAndHttpMessage(
      port,
      new ServerHttpMessage(getHost(), port).toString());

    // Creates a close dependency between the current Server and its internalServer.
    createCloseDependencyTo(internalServer);
  }

  /**
   * @return a new {@link Server} that will listen to net {@link AbstractClient}s
   *         on the HTTP port (80).
   */
  public static Server forHttpPort() {
    return forPort(PortCatalog.HTTP);
  }

  /**
   * @param port
   * @return a new {@link Server} that will listen to net {@link AbstractClient}s
   *         on the given port
   * @throws RuntimeException if the given port is not in [0, 65535]
   */
  public static Server forPort(final int port) {
    return new Server(port);
  }

  /**
   * @return the current {@link Server} as {@link IServerTarget}.
   */
  @Override
  public IServerTarget asTarget() {
    return ServerTarget.forIpOrDomainAndPortAndSecurityModeForConnections(
      getHost(),
      getPort(),
      SECURITY_MODE_FOR_CONNECTIONS);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getHost() {
    return LocalComputer.getLanIp();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getPort() {
    return internalServer.getPort();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public SecurityMode getSecurityMode() {
    return internalServer.getSecurityMode();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected Server asConcrete() {
    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void noteAddedApplication(final AbstractApplication<?, ?> application) {
    internalServer.addSlot(ExecutorAndDataProviderSlot.withNameAndParentServer(application.getUrlInstanceName(), this));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void noteAddedDefaultApplication(final AbstractApplication<?, ?> defaultApplication) {
    final var executorAndDataProviderSlot = //
    ExecutorAndDataProviderSlot.withNameAndParentServer(defaultApplication.getUrlInstanceName(), this);

    internalServer.addDefaultSlot(executorAndDataProviderSlot);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void noteRemovedApplication(final Application<?, ?> application) {
    internalServer.removeSlotByName(application.getUrlInstanceName());
  }
}
