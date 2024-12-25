package ch.nolix.system.application.main;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.net.endpoint3.LocalEndPoint;
import ch.nolix.core.net.endpoint3.NetEndPoint;

/**
 * @author Silvan Wyss
 * @version 2022-03-18
 * @param <C> is the type of a {@link FrontendClient}.
 */
public abstract class FrontendClient<C extends FrontendClient<C>> extends AbstractClient<C> {

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean isBackendClient() {
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean isFrontendClient() {
    return true;
  }

  /**
   * Connects the current {@link FrontendClient} to the given application.
   * 
   * @param application
   * @throws InvalidArgumentException if the current {@link FrontendClient} is
   *                                  already connected.
   */
  protected final void connectTo(final Application<?, ?> application) {

    final var endPoint = new LocalEndPoint();

    internalSetEndPoint(endPoint);

    application.takeEndPoint(endPoint.getStoredCounterpart());
  }

  /**
   * Connects the current {@link FrontendClient} to the default
   * {@link Application} on the given server.
   * 
   * @param server
   * @throws InvalidArgumentException if the current {@link FrontendClient} is
   *                                  already connected.
   */
  protected final void connectTo(final BaseServer<?> server) {

    final var endPoint = new LocalEndPoint();

    internalSetEndPoint(endPoint);

    server.getStoredDefaultApplication().takeEndPoint(endPoint.getStoredCounterpart());
  }

  /**
   * Connects the current {@link FrontendClient} to the default
   * {@link Application} on the given port on the local computer.
   * 
   * @param port
   * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
   * @throws InvalidArgumentException      if the current {@link FrontendClient}
   *                                       is already connected.
   */
  protected final void connectTo(final int port) {
    internalSetEndPoint(new NetEndPoint(port));
  }

  /**
   * Connects the current {@link FrontendClient} to the {@link Application} with
   * the given name on the given port on the local computer.
   * 
   * @param port
   * @param name
   * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
   * @throws ArgumentIsNullException       if the given name is null.
   * @throws EmptyArgumentException        if the given name is blank.
   * @throws InvalidArgumentException      if the current {@link FrontendClient}
   *                                       is already connected.
   */
  protected final void connectTo(final int port, final String name) {
    internalSetEndPoint(new NetEndPoint(port, name));
  }

  /**
   * Connects the current {@link FrontendClient} to the {@link Application} with
   * the given instanceName on the given server.
   * 
   * @param server
   * @param instanceName
   * @throws ArgumentIsNullException  if the given instanceName is null.
   * @throws EmptyArgumentException   if the given instanceName is blank.
   * @throws InvalidArgumentException if the current {@link FrontendClient} is
   *                                  already connected.
   */
  protected final void connectTo(final BaseServer<?> server, final String instanceName) {

    final var endPoint = new LocalEndPoint();

    internalSetEndPoint(endPoint);

    server.getStoredApplicationByInstanceName(instanceName).takeEndPoint(endPoint.getStoredCounterpart());
  }

  /**
   * Connects the current {@link FrontendClient} to the default
   * {@link Application} on the HTTP port (80) on the computer with the given ip.
   * 
   * @param ip
   * @throws InvalidArgumentException if the current {@link FrontendClient} is
   *                                  already connected.
   */
  protected final void connectTo(final String ip) {
    internalSetEndPoint(new NetEndPoint(ip));
  }

  /**
   * Connects the current {@link FrontendClient} to the default
   * {@link Application} on given port on the computer with the given ip.
   * 
   * @param ip
   * @param port
   * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
   * @throws InvalidArgumentException      if the current {@link FrontendClient}
   *                                       is already connected.
   */
  protected final void connectTo(final String ip, final int port) {
    internalSetEndPoint(new NetEndPoint(ip, port));
  }

  /**
   * Connects the current {@link FrontendClient} to the {@link Application} with
   * the given name on the given port on the computer with the given ip.
   * 
   * @param ip
   * @param port
   * @param name
   * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
   * @throws ArgumentIsNullException       if the given name is null.
   * @throws EmptyArgumentException        if the given name is blank.
   * @throws InvalidArgumentException      if the current {@link FrontendClient}
   *                                       is already connected.
   */
  protected final void connectTo(final String ip, final int port, final String name) {
    internalSetEndPoint(new NetEndPoint(ip, port, name));
  }
}
