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
public abstract class AbstractServerTarget implements IServerTarget {
  private final String host;

  private final int port;

  private final SecurityMode securityModeForConnections;

  protected AbstractServerTarget(
    final String host,
    final int port,
    final SecurityMode securityModeForConnections) {
    Validator.assertThat(host).thatIsNamed("ip or address name").isNotBlank();
    Validator.assertThat(port).thatIsNamed(LowerCaseVariableNameCatalog.PORT).isPort();

    Validator
      .assertThat(securityModeForConnections)
      .thatIsNamed("security mode for connections")
      .isNotNull();

    this.host = host;
    this.port = port;
    this.securityModeForConnections = securityModeForConnections;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final String getHost() {
    return host;
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
