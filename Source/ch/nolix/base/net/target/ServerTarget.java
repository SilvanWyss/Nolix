/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.target;

import ch.nolix.baseapi.net.netproperty.SecurityMode;

/**
 * @author Silvan Wyss
 */
public final class ServerTarget extends AbstractServerTarget {
  private ServerTarget(final String host, final int port, final SecurityMode securityModeForConnections) {
    super(host, port, securityModeForConnections);
  }

  /**
   * @param host
   * @param port
   * @param securityModeForConnections
   * @return a new {@link ServerTarget} with the given host, port and
   *         securityModeForConnections
   */
  public static ServerTarget forHostAndPortAndSecurityModeForConnections(
    final String host,
    final int port,
    final SecurityMode securityModeForConnections) {
    return new ServerTarget(host, port, securityModeForConnections);
  }
}
