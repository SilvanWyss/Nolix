/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.target;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.baseapi.net.netcatalog.PortCatalog;
import ch.nolix.baseapi.net.netproperty.SecurityMode;
import ch.nolix.baseapi.net.target.IServerTarget;

/**
 * @author Silvan Wyss
 */
public class ServerTarget implements IServerTarget {
  private final String ipOrDomain;

  private final int port;

  private final SecurityMode securityModeForConnections;

  protected ServerTarget(
    final String ipOrDomain,
    final int port,
    final SecurityMode securityModeForConnections) {
    Validator.assertThat(ipOrDomain).thatIsNamed("ip or address name").isNotBlank();
    Validator.assertThat(port).thatIsNamed(LowerCaseVariableNameCatalog.PORT).isPort();

    Validator
      .assertThat(securityModeForConnections)
      .thatIsNamed("security mode for connections")
      .isNotNull();

    this.ipOrDomain = ipOrDomain;
    this.port = port;
    this.securityModeForConnections = securityModeForConnections;
  }

  public static ServerTarget forIpOrDomainAndPortAndSecurityModeForConnections(
    final String ipOrDomain,
    final int port,
    final SecurityMode securityModeForConnections) {
    return new ServerTarget(ipOrDomain, port, securityModeForConnections);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final String getHost() {
    return ipOrDomain;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final int getPort() {
    return port;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final SecurityMode getSecurityMode() {
    return securityModeForConnections;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toUrl() {
    return switch (getSecurityMode()) {
      case NONE ->
        toHttpUrl();
      case SSL ->
        toHttpsUrl();
    };
  }

  private String toHttpsUrl() {
    if (getPort() == PortCatalog.HTTPS) {
      return String.format("https://%s", getHost());
    }

    return String.format("https://%s:%s", getHost(), getPort());
  }

  private String toHttpUrl() {
    if (getPort() == PortCatalog.HTTP) {
      return String.format("http://%s", getHost());
    }

    return String.format("http://%s:%s", getHost(), getPort());
  }
}
