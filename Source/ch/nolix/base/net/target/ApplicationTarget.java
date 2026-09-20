/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.target;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.net.netproperty.SecurityMode;
import ch.nolix.baseapi.net.target.IApplicationTarget;

/**
 * @author Silvan Wyss
 */
public final class ApplicationTarget extends AbstractServerTarget implements IApplicationTarget {
  private final String applicationName;

  private final String urlApplicationName;

  protected ApplicationTarget(
    final String host,
    final int port,
    final String applicationName,
    final String urlApplicationName,
    final SecurityMode securityModeForConnections) {
    super(host, port, securityModeForConnections);

    Validator.assertThat(applicationName).thatIsNamed("application instance name").isNotBlank();
    Validator.assertThat(urlApplicationName).thatIsNamed("application url instance name").isNotBlank();

    this.applicationName = applicationName;
    this.urlApplicationName = urlApplicationName;
  }

  public static ApplicationTarget//
  forHostAndPortAndApplicationInstanceNameAndApplicationUrlInstanceNameAndSecurityModeForConnections(
    final String host,
    final int port,
    final String applicationInstanceName,
    final String applicationUrlInstanceName,
    final SecurityMode securityModeForConnections) {
    return new ApplicationTarget(
      host,
      port,
      applicationInstanceName,
      applicationUrlInstanceName,
      securityModeForConnections);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getApplicationname() {
    return applicationName;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getUrlApplicationName() {
    return urlApplicationName;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toUrl() {
    return (super.toUrl() + "?app=" + getUrlApplicationName());
  }
}
