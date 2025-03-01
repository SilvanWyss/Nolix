package ch.nolix.core.net.target;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.netapi.netconstantapi.PortCatalog;
import ch.nolix.coreapi.netapi.securityproperty.SecurityMode;
import ch.nolix.coreapi.netapi.targetapi.IServerTarget;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;

public class ServerTarget implements IServerTarget {

  private final String ipOrDomain;

  private final int port;

  private final SecurityMode securityModeForConnections;

  protected ServerTarget(
    final String ipOrDomain,
    final int port,
    final SecurityMode securityModeForConnections) {

    Validator.assertThat(ipOrDomain).thatIsNamed("ip or address name").isNotBlank();
    Validator.assertThat(port).thatIsNamed(LowerCaseVariableCatalog.PORT).isPort();

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

    if (getPort() == PortCatalog.HTTPS) {
      return String.format("https://%s", getIpOrDomain());
    }

    return String.format("https://%s:%s", getIpOrDomain(), getPort());
  }

  private String toHttpUrl() {

    if (getPort() == PortCatalog.HTTP) {
      return String.format("http://%s", getIpOrDomain());
    }

    return String.format("http://%s:%s", getIpOrDomain(), getPort());
  }
}
