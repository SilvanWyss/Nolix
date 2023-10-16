//package declaration
package ch.nolix.system.application.main;

//own imports
import ch.nolix.core.net.constant.PortCatalogue;
import ch.nolix.core.net.target.ServerTarget;
import ch.nolix.core.net.tls.NolixConfigurationSSLCertificateReader;
import ch.nolix.coreapi.netapi.tlsapi.ISSLCertificate;
import ch.nolix.coreapi.programcontrolapi.processproperty.SecurityLevel;
import ch.nolix.coreapi.programcontrolapi.targetapi.IServerTarget;

//class
public final class SecureServer extends BaseServer<SecureServer> {

  //constant
  public static final int DEFAULT_PORT = PortCatalogue.HTTPS;

  //constant
  private static final SecurityLevel SECURITY_LEVEL_FOR_CONNECTIONS = SecurityLevel.SECURE;

  //constant
  private static final NolixConfigurationSSLCertificateReader NOLIX_CONFIUGEATION_SSL_CERTIFICATE_READER = //
      new NolixConfigurationSSLCertificateReader();

  //static method
  public static SecureServer forHttpsPortAndDomainAndSSLCertificateFromNolixConfiguration() {

    final var domain = NOLIX_CONFIUGEATION_SSL_CERTIFICATE_READER.getDefaultDomainFromLocalNolixConfiguration();

    final var paramSSLCertificate = NOLIX_CONFIUGEATION_SSL_CERTIFICATE_READER
        .getDefaultSSLCertificatefromLocalNolixConfiguration();

    return new SecureServer(PortCatalogue.HTTPS, domain, paramSSLCertificate);
  }

  //static method
  public static SecureServer forDefaultPortAndDomainAndSSLCertificateFromNolixConfiguration(
      final String domain) {

    final var paramSSLCertificate = NOLIX_CONFIUGEATION_SSL_CERTIFICATE_READER
        .getDefaultSSLCertificatefromLocalNolixConfiguration();

    return new SecureServer(DEFAULT_PORT, domain, paramSSLCertificate);
  }

  //static method
  public static SecureServer forPortAndDomainAndSSLCertificateFromNolixConfiguration(
      final int port,
      final String domain) {

    final var paramSSLCertificate = NOLIX_CONFIUGEATION_SSL_CERTIFICATE_READER
        .getDefaultSSLCertificatefromLocalNolixConfiguration();

    return new SecureServer(port, domain, paramSSLCertificate);
  }

  //attribute
  private final ch.nolix.core.net.endpoint3.SecureServer internalWebSocketServer;

  //attribute
  private final String domain;

  //attribute
  private final int port;

  //constructor
  public SecureServer(final int port, final String domain, final ISSLCertificate paramSSLCertificate) {

    final var htmlPage = new SecureServerHtmlPage(domain, port);
    final var htmlPageAsString = htmlPage.toString();

    internalWebSocketServer = new ch.nolix.core.net.endpoint3.SecureServer(port, htmlPageAsString, paramSSLCertificate);

    this.domain = domain;
    this.port = port;

    createCloseDependencyTo(internalWebSocketServer);
  }

  //method
  @Override
  public IServerTarget asTarget() {
    return ServerTarget.forIpOrDomainAndPortAndSecurityLevelForConnections(
        domain,
        port,
        SECURITY_LEVEL_FOR_CONNECTIONS);
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  protected SecureServer asConcrete() {
    return this;
  }

  //method
  @Override
  protected void noteAddedApplication(final Application<?, ?> application) {
    internalWebSocketServer.addSlot(new ServerEndPointTaker(application.getUrlInstanceName(), this));
  }

  //method
  @Override
  protected void noteAddedDefaultApplication(final Application<?, ?> defaultApplication) {
    internalWebSocketServer.addDefaultSlot(new ServerEndPointTaker(defaultApplication.getUrlInstanceName(), this));
  }
}
