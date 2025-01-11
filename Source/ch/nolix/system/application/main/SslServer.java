package ch.nolix.system.application.main;

import ch.nolix.core.net.ssl.NolixConfigurationSSLCertificateReader;
import ch.nolix.core.net.target.ServerTarget;
import ch.nolix.coreapi.netapi.netconstantapi.PortCatalog;
import ch.nolix.coreapi.netapi.securityproperty.SecurityMode;
import ch.nolix.coreapi.netapi.sslapi.ISslCertificate;
import ch.nolix.coreapi.netapi.targetapi.IServerTarget;
import ch.nolix.systemapi.applicationapi.mainapi.IApplication;

public final class SslServer extends AbstractServer<SslServer> {

  public static final int DEFAULT_PORT = PortCatalog.HTTPS;

  private static final SecurityMode SECURITY_MODE_FOR_CONNECTIONS = SecurityMode.SSL;

  private static final NolixConfigurationSSLCertificateReader NOLIX_CONFIUGEATION_SSL_CERTIFICATE_READER = //
  new NolixConfigurationSSLCertificateReader();

  private final ch.nolix.core.net.endpoint3.SslServer internalWebSocketServer;

  private final String domain;

  private final int port;

  public SslServer(final int port, final String domain, final ISslCertificate paramSSLCertificate) {

    final var htmlPage = SslServerPage.forDomainAndPort(domain, port);
    final var htmlPageAsString = htmlPage.toString();

    internalWebSocketServer = new ch.nolix.core.net.endpoint3.SslServer(port, htmlPageAsString, paramSSLCertificate);

    this.domain = domain;
    this.port = port;

    createCloseDependencyTo(internalWebSocketServer);
  }

  public static SslServer forHttpsPortAndDomainAndSSLCertificateFromNolixConfiguration() {

    final var domain = NOLIX_CONFIUGEATION_SSL_CERTIFICATE_READER.getDefaultDomainFromLocalNolixConfiguration();

    final var paramSSLCertificate = NOLIX_CONFIUGEATION_SSL_CERTIFICATE_READER
      .getDefaultSSLCertificatefromLocalNolixConfiguration();

    return new SslServer(PortCatalog.HTTPS, domain, paramSSLCertificate);
  }

  public static SslServer forDefaultPortAndDomainAndSSLCertificateFromNolixConfiguration(
    final String domain) {

    final var paramSSLCertificate = NOLIX_CONFIUGEATION_SSL_CERTIFICATE_READER
      .getDefaultSSLCertificatefromLocalNolixConfiguration();

    return new SslServer(DEFAULT_PORT, domain, paramSSLCertificate);
  }

  public static SslServer forPortAndDomainAndSSLCertificateFromNolixConfiguration(
    final int port,
    final String domain) {

    final var paramSSLCertificate = NOLIX_CONFIUGEATION_SSL_CERTIFICATE_READER
      .getDefaultSSLCertificatefromLocalNolixConfiguration();

    return new SslServer(port, domain, paramSSLCertificate);
  }

  @Override
  public IServerTarget asTarget() {
    return ServerTarget.forIpOrDomainAndPortAndSecurityModeForConnections(
      domain,
      port,
      SECURITY_MODE_FOR_CONNECTIONS);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public SecurityMode getSecurityMode() {
    return internalWebSocketServer.getSecurityMode();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected SslServer asConcrete() {
    return this;
  }

  @Override
  protected void noteAddedApplication(final Application<?, ?> application) {
    internalWebSocketServer.addSlot(new ServerSlot(application.getUrlInstanceName(), this));
  }

  @Override
  protected void noteAddedDefaultApplication(final Application<?, ?> defaultApplication) {
    internalWebSocketServer.addDefaultSlot(new ServerSlot(defaultApplication.getUrlInstanceName(), this));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void noteRemovedApplication(final IApplication<?> application) {
    internalWebSocketServer.removeSlotByName(application.getUrlInstanceName());
  }
}
