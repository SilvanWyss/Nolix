//package declaration
package ch.nolix.core.net.target;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.net.constant.PortCatalogue;
import ch.nolix.coreapi.netapi.securityapi.SecurityMode;
import ch.nolix.coreapi.programatomapi.variablenameapi.LowerCaseCatalogue;
import ch.nolix.coreapi.programcontrolapi.targetapi.IServerTarget;

//class
public class ServerTarget implements IServerTarget {

  //attribute
  private final String ipOrDomain;

  //attribute
  private final int port;

  //attribute
  private final SecurityMode securityLevelForConnections;

  //constructor
  protected ServerTarget(
    final String ipOrDomain,
    final int port,
    final SecurityMode securityLevelForConnections) {

    GlobalValidator.assertThat(ipOrDomain).thatIsNamed("ip or address name").isNotBlank();
    GlobalValidator.assertThat(port).thatIsNamed(LowerCaseCatalogue.PORT).isPort();

    GlobalValidator
      .assertThat(securityLevelForConnections)
      .thatIsNamed("security level for connections")
      .isNotNull();

    this.ipOrDomain = ipOrDomain;
    this.port = port;
    this.securityLevelForConnections = securityLevelForConnections;
  }

  //static method
  public static ServerTarget forIpOrDomainAndPortAndSecurityLevelForConnections(
    final String ipOrDomain,
    final int port,
    final SecurityMode securityLevelForConnections) {
    return new ServerTarget(ipOrDomain, port, securityLevelForConnections);
  }

  //method
  @Override
  public final String getIpOrDomain() {
    return ipOrDomain;
  }

  //method
  @Override
  public final int getPort() {
    return port;
  }

  //method
  @Override
  public final SecurityMode getSecurityLevelForConnections() {
    return securityLevelForConnections;
  }

  //method
  @Override
  public String toUrl() {
    return switch (getSecurityLevelForConnections()) {
      case NONE ->
        toHttpUrl();
      case SSL ->
        toHttpsUrl();
    };
  }

  //method
  private String toHttpsUrl() {

    if (getPort() == PortCatalogue.HTTPS) {
      return String.format("https://%s", getIpOrDomain());
    }

    return String.format("https://%s:%s", getIpOrDomain(), getPort());
  }

  //method
  private String toHttpUrl() {

    if (getPort() == PortCatalogue.HTTP) {
      return String.format("http://%s", getIpOrDomain());
    }

    return String.format("http://%s:%s", getIpOrDomain(), getPort());
  }
}
