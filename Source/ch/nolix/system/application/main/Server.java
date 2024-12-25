package ch.nolix.system.application.main;

import ch.nolix.core.environment.localcomputer.LocalComputer;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.core.net.target.ServerTarget;
import ch.nolix.coreapi.netapi.netconstantapi.PortCatalogue;
import ch.nolix.coreapi.netapi.securityproperty.SecurityMode;
import ch.nolix.coreapi.netapi.targetapi.IServerTarget;
import ch.nolix.systemapi.applicationapi.mainapi.IApplication;

/**
 * A {@link Server} is a {@link BaseServer} that listens to net
 * {@link AbstractClient}s on a specific port.
 * 
 * @author Silvan Wyss
 * @version 2017-09-10
 */
public final class Server extends BaseServer<Server> {

  private static final SecurityMode SECURITY_MODE_FOR_CONNECTIONS = SecurityMode.NONE;

  private ch.nolix.core.net.endpoint3.Server internalServer;

  /**
   * Creates a new {@link Server} that will listen to net {@link AbstractClient}s
   * on the given port.
   * 
   * @param port
   * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
   */
  private Server(final int port) {

    //Creates the internalServer of the current Server.
    internalServer = ch.nolix.core.net.endpoint3.Server.forPortAndHttpMessage(
      port,
      new ServerHttpMessage(getIp(), port).toString());

    //Creates a close dependency between the current Server and its internalServer.
    createCloseDependencyTo(internalServer);
  }

  /**
   * @return a new {@link Server} that will listen to net {@link AbstractClient}s
   *         on the HTTP port (80).
   */
  public static Server forHttpPort() {
    return forPort(PortCatalogue.HTTP);
  }

  /**
   * @param port
   * @return a new {@link Server} that will listen to net {@link AbstractClient}s
   *         on the given port.
   * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
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
      getIp(),
      getPort(),
      SECURITY_MODE_FOR_CONNECTIONS);
  }

  /**
   * @return the Ip of the current {@link Server}.
   */
  public String getIp() {
    return LocalComputer.getLanIp();
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
  protected void noteAddedApplication(final Application<?, ?> application) {
    internalServer.addSlot(new ServerSlot(application.getUrlInstanceName(), this));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void noteAddedDefaultApplication(final Application<?, ?> defaultApplication) {
    internalServer.addDefaultSlot(new ServerSlot(defaultApplication.getUrlInstanceName(), this));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void noteRemovedApplication(final IApplication<?> application) {
    internalServer.removeSlotByName(application.getUrlInstanceName());
  }
}
