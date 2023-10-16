//package declaration
package ch.nolix.core.net.target;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programcontrolapi.processproperty.SecurityLevel;
import ch.nolix.coreapi.programcontrolapi.targetapi.IApplicationInstanceTarget;

//class
public class ApplicationInstanceTarget extends ServerTarget implements IApplicationInstanceTarget {

  //static method
  public static ApplicationInstanceTarget//
      forIpOrDomainAndPortAndApplicationInstanceNameAndApplicationUrlInstanceNameAndSecurityLevelForConnections(
          final String ipOrDomain,
          final int port,
          final String applicationInstanceName,
          final String applicationUrlInstanceName,
          final SecurityLevel securityLevelForConnections) {
    return new ApplicationInstanceTarget(
        ipOrDomain,
        port,
        applicationInstanceName,
        applicationUrlInstanceName,
        securityLevelForConnections);
  }

  //attribute
  private final String applicationInstanceName;

  //attribute
  private final String applicationUrlInstanceName;

  //constructor
  protected ApplicationInstanceTarget(
      final String ipOrDomain,
      final int port,
      final String applicationInstanceName,
      final String applicationUrlInstanceName,
      final SecurityLevel securityLevelForConnections) {

    super(ipOrDomain, port, securityLevelForConnections);

    GlobalValidator.assertThat(applicationInstanceName).thatIsNamed("application instance name").isNotBlank();
    GlobalValidator.assertThat(applicationUrlInstanceName).thatIsNamed("application url instance name").isNotBlank();

    this.applicationInstanceName = applicationInstanceName;
    this.applicationUrlInstanceName = applicationUrlInstanceName;
  }

  //method
  @Override
  public final String getApplicationInstanceName() {
    return applicationInstanceName;
  }

  //method
  @Override
  public final String getApplicationUrlInstanceName() {
    return applicationUrlInstanceName;
  }

  //method
  @Override
  public String toUrl() {
    return (super.toUrl() + "?app=" + getApplicationUrlInstanceName());
  }
}
