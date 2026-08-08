/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.clientserver;

import ch.nolix.base.net.executoranddataproviderserver.LocalEndPoint;
import ch.nolix.base.net.executoranddataproviderserver.NetEndPoint;

/**
 * @author Silvan Wyss
 * @param <C> the type of a {@link AbstractFrontendClient}.
 */
public abstract class AbstractFrontendClient<C extends AbstractFrontendClient<C>> extends AbstractClient<C> {
  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean isBackendClient() {
    return false;
  }

  /**
   * Connects the current {@link AbstractFrontendClient} to the given application.
   * 
   * @param application
   * @throws RuntimeException if the current {@link AbstractFrontendClient} is
   *                          already connected.
   */
  protected final void connectTo(final AbstractApplication<?, ?> application) {
    final var endPoint = new LocalEndPoint();

    setEndPoint(endPoint);

    application.takeEndPoint(endPoint.getStoredCounterpart());
  }

  /**
   * Connects the current {@link AbstractFrontendClient} to the default
   * {@link AbstractApplication} on the given server.
   * 
   * @param server
   * @throws RuntimeException if the current {@link AbstractFrontendClient} is
   *                          already connected.
   */
  protected final void connectTo(final AbstractServer<?> server) {
    final var endPoint = new LocalEndPoint();

    setEndPoint(endPoint);

    server.getStoredDefaultApplication().takeEndPoint(endPoint.getStoredCounterpart());
  }

  /**
   * Connects the current {@link AbstractFrontendClient} to the default
   * {@link AbstractApplication} on the given port on the local computer.
   * 
   * @param port
   * @throws RuntimeException if the given port is not in [0, 65535]
   * @throws RuntimeException if the current {@link AbstractFrontendClient} is
   *                          already connected.
   */
  protected final void connectTo(final int port) {
    setEndPoint(NetEndPoint.toLocalMachineAndGivenPortAndDefaultSlot(port));
  }

  /**
   * Connects the current {@link AbstractFrontendClient} to the
   * {@link AbstractApplication} with the given name on the given port on the
   * local computer.
   * 
   * @param port
   * @param name
   * @throws RuntimeException if the given port is not in [0, 65535]
   * @throws RuntimeException if the given name is null
   * @throws RuntimeException if the given name is blank
   * @throws RuntimeException if the current {@link AbstractFrontendClient} is
   *                          already connected.
   */
  protected final void connectTo(final int port, final String name) {
    setEndPoint(NetEndPoint.toLocalMachineAndGivenPortAndGivenSlot(port, name));
  }

  /**
   * Connects the current {@link AbstractFrontendClient} to the
   * {@link AbstractApplication} with the given instanceName on the given server.
   * 
   * @param server
   * @param instanceName
   * @throws RuntimeException if the given instanceName is null
   * @throws RuntimeException if the given instanceName is blank
   * @throws RuntimeException if the current {@link AbstractFrontendClient} is
   *                          already connected.
   */
  protected final void connectTo(final AbstractServer<?> server, final String instanceName) {
    final var endPoint = new LocalEndPoint();

    setEndPoint(endPoint);

    server.getStoredApplicationByInstanceName(instanceName).takeEndPoint(endPoint.getStoredCounterpart());
  }

  /**
   * Connects the current {@link AbstractFrontendClient} to the default
   * {@link AbstractApplication} on the HTTP port (80) on the computer with the
   * given ip.
   * 
   * @param ip
   * @throws RuntimeException if the current {@link AbstractFrontendClient} is
   *                          already connected.
   */
  protected final void connectTo(final String ip) {
    setEndPoint(NetEndPoint.toGivenHostAndHttpPortAndDefaultSlot(ip));
  }

  /**
   * Connects the current {@link AbstractFrontendClient} to the default
   * {@link AbstractApplication} on given port on the computer with the given ip.
   * 
   * @param ip
   * @param port
   * @throws RuntimeException if the given port is not in [0, 65535]
   * @throws RuntimeException if the current {@link AbstractFrontendClient} is
   *                          already connected.
   */
  protected final void connectTo(final String ip, final int port) {
    setEndPoint(NetEndPoint.toGivenHostAndGivenPortAndDefaultSlot(ip, port));
  }

  /**
   * Connects the current {@link AbstractFrontendClient} to the
   * {@link AbstractApplication} with the given name on the given port on the
   * computer with the given ip.
   * 
   * @param ip
   * @param port
   * @param name
   * @throws RuntimeException if the given port is not in [0, 65535]
   * @throws RuntimeException if the given name is null
   * @throws RuntimeException if the given name is blank
   * @throws RuntimeException if the current {@link AbstractFrontendClient} is
   *                          already connected.
   */
  protected final void connectTo(final String ip, final int port, final String name) {
    setEndPoint(NetEndPoint.toGivenHostAndGivenPortAndGivenSlot(ip, port, name));
  }
}
