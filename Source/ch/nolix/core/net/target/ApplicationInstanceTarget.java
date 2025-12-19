package ch.nolix.core.net.target;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.net.securityproperty.SecurityMode;
import ch.nolix.coreapi.net.target.IApplicationInstanceTarget;

/**
 * @author Silvan Wyss
 */
public class ApplicationInstanceTarget extends ServerTarget implements IApplicationInstanceTarget {
  private final String applicationInstanceName;

  private final String applicationUrlInstanceName;

  protected ApplicationInstanceTarget(
    final String ipOrDomain,
    final int port,
    final String applicationInstanceName,
    final String applicationUrlInstanceName,
    final SecurityMode securityModeForConnections) {
    super(ipOrDomain, port, securityModeForConnections);

    Validator.assertThat(applicationInstanceName).thatIsNamed("application instance name").isNotBlank();
    Validator.assertThat(applicationUrlInstanceName).thatIsNamed("application url instance name").isNotBlank();

    this.applicationInstanceName = applicationInstanceName;
    this.applicationUrlInstanceName = applicationUrlInstanceName;
  }

  public static ApplicationInstanceTarget//
  forIpOrDomainAndPortAndApplicationInstanceNameAndApplicationUrlInstanceNameAndSecurityModeForConnections(
    final String ipOrDomain,
    final int port,
    final String applicationInstanceName,
    final String applicationUrlInstanceName,
    final SecurityMode securityModeForConnections) {
    return new ApplicationInstanceTarget(
      ipOrDomain,
      port,
      applicationInstanceName,
      applicationUrlInstanceName,
      securityModeForConnections);
  }

  @Override
  public final String getApplicationInstanceName() {
    return applicationInstanceName;
  }

  @Override
  public final String getApplicationUrlInstanceName() {
    return applicationUrlInstanceName;
  }

  @Override
  public String toUrl() {
    return (super.toUrl() + "?app=" + getApplicationUrlInstanceName());
  }
}
