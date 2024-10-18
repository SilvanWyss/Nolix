package ch.nolix.core.net.target;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.netapi.netconstantapi.PortCatalogue;
import ch.nolix.coreapi.netapi.securityproperty.SecurityMode;
import ch.nolix.coreapi.netapi.targetapi.IServerTarget;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

public class ServerTarget implements IServerTarget {

  private final String ipOrDomain;

  private final int port;

  private final SecurityMode securityModeForConnections;

  protected ServerTarget(
    final String ipOrDomain,
    final int port,
    final SecurityMode securityModeForConnections) {

    GlobalValidator.assertThat(ipOrDomain).thatIsNamed("ip or address name").isNotBlank();
    GlobalValidator.assertThat(port).thatIsNamed(LowerCaseVariableCatalogue.PORT).isPort();

    GlobalValidator
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

  @Override
  public final String getIpOrDomain() {
    return ipOrDomain;
  }

  @Override
  public final int getPort() {
    return port;
  }

  @Override
  public final SecurityMode getSecurityModeForConnection() {
    return securityModeForConnections;
  }

  @Override
  public String toUrl() {
    return switch (getSecurityModeForConnection()) {
      case NONE ->
        toHttpUrl();
      case SSL ->
        toHttpsUrl();
    };
  }

  private String toHttpsUrl() {

    if (getPort() == PortCatalogue.HTTPS) {
      return String.format("https://%s", getIpOrDomain());
    }

    return String.format("https://%s:%s", getIpOrDomain(), getPort());
  }

  private String toHttpUrl() {

    if (getPort() == PortCatalogue.HTTP) {
      return String.format("http://%s", getIpOrDomain());
    }

    return String.format("http://%s:%s", getIpOrDomain(), getPort());
  }
}
