/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.target;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.net.netproperty.SecurityMode;
import ch.nolix.baseapi.net.target.IApplicationInstanceTarget;

/**
 * @author Silvan Wyss
 */
public class ApplicationInstanceTarget extends AbstractServerTarget implements IApplicationInstanceTarget {
  private final String applicationInstanceName;

  private final String applicationUrlInstanceName;

  protected ApplicationInstanceTarget(
    final String host,
    final int port,
    final String applicationInstanceName,
    final String applicationUrlInstanceName,
    final SecurityMode securityModeForConnections) {
    super(host, port, securityModeForConnections);

    Validator.assertThat(applicationInstanceName).thatIsNamed("application instance name").isNotBlank();
    Validator.assertThat(applicationUrlInstanceName).thatIsNamed("application url instance name").isNotBlank();

    this.applicationInstanceName = applicationInstanceName;
    this.applicationUrlInstanceName = applicationUrlInstanceName;
  }

  public static ApplicationInstanceTarget//
  forHostAndPortAndApplicationInstanceNameAndApplicationUrlInstanceNameAndSecurityModeForConnections(
    final String host,
    final int port,
    final String applicationInstanceName,
    final String applicationUrlInstanceName,
    final SecurityMode securityModeForConnections) {
    return new ApplicationInstanceTarget(
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
  public final String getApplicationInstanceName() {
    return applicationInstanceName;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final String getApplicationUrlInstanceName() {
    return applicationUrlInstanceName;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toUrl() {
    return (super.toUrl() + "?app=" + getApplicationUrlInstanceName());
  }
}
