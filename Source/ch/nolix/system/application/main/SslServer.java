//package declaration
package ch.nolix.system.application.main;

import ch.nolix.core.net.ssl.NolixConfigurationSSLCertificateReader;
import ch.nolix.core.net.target.ServerTarget;
import ch.nolix.coreapi.netapi.netconstantapi.PortCatalogue;
import ch.nolix.coreapi.netapi.securityproperty.SecurityMode;
import ch.nolix.coreapi.netapi.sslapi.ISslCertificate;
import ch.nolix.coreapi.programcontrolapi.targetapi.IServerTarget;
import ch.nolix.systemapi.applicationapi.mainapi.IApplication;

//class
public final class SslServer extends BaseServer<SslServer> {

  //constant
  public static final int DEFAULT_PORT = PortCatalogue.HTTPS;

  //constant
  private static final SecurityMode SECURITY_LEVEL_FOR_CONNECTIONS = SecurityMode.SSL;

  //constant
  private static final NolixConfigurationSSLCertificateReader NOLIX_CONFIUGEATION_SSL_CERTIFICATE_READER = //
  new NolixConfigurationSSLCertificateReader();

  //attribute
  private final ch.nolix.core.net.endpoint3.SslServer internalWebSocketServer;

  //attribute
  private final String domain;

  //attribute
  private final int port;

  //constructor
  public SslServer(final int port, final String domain, final ISslCertificate paramSSLCertificate) {

    final var htmlPage = new SslServerHtmlPage(domain, port);
    final var htmlPageAsString = htmlPage.toString();

    internalWebSocketServer = new ch.nolix.core.net.endpoint3.SslServer(port, htmlPageAsString, paramSSLCertificate);

    this.domain = domain;
    this.port = port;

    createCloseDependencyTo(internalWebSocketServer);
  }

  //static method
  public static SslServer forHttpsPortAndDomainAndSSLCertificateFromNolixConfiguration() {

    final var domain = NOLIX_CONFIUGEATION_SSL_CERTIFICATE_READER.getDefaultDomainFromLocalNolixConfiguration();

    final var paramSSLCertificate = NOLIX_CONFIUGEATION_SSL_CERTIFICATE_READER
      .getDefaultSSLCertificatefromLocalNolixConfiguration();

    return new SslServer(PortCatalogue.HTTPS, domain, paramSSLCertificate);
  }

  //static method
  public static SslServer forDefaultPortAndDomainAndSSLCertificateFromNolixConfiguration(
    final String domain) {

    final var paramSSLCertificate = NOLIX_CONFIUGEATION_SSL_CERTIFICATE_READER
      .getDefaultSSLCertificatefromLocalNolixConfiguration();

    return new SslServer(DEFAULT_PORT, domain, paramSSLCertificate);
  }

  //static method
  public static SslServer forPortAndDomainAndSSLCertificateFromNolixConfiguration(
    final int port,
    final String domain) {

    final var paramSSLCertificate = NOLIX_CONFIUGEATION_SSL_CERTIFICATE_READER
      .getDefaultSSLCertificatefromLocalNolixConfiguration();

    return new SslServer(port, domain, paramSSLCertificate);
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
  public SecurityMode getSecurityLevel() {
    return internalWebSocketServer.getSecurityLevel();
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  protected SslServer asConcrete() {
    return this;
  }

  //method
  @Override
  protected void noteAddedApplication(final Application<?, ?> application) {
    internalWebSocketServer.addSlot(new ServerSlot(application.getUrlInstanceName(), this));
  }

  //method
  @Override
  protected void noteAddedDefaultApplication(final Application<?, ?> defaultApplication) {
    internalWebSocketServer.addDefaultSlot(new ServerSlot(defaultApplication.getUrlInstanceName(), this));
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  protected void noteRemovedApplication(final IApplication<?> application) {
    internalWebSocketServer.removeSlotByName(application.getUrlInstanceName());
  }
}
