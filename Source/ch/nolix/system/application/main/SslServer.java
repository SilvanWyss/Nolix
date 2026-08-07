/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.application.main;

import ch.nolix.base.net.ssl.NolixConfigurationSslCertificateReader;
import ch.nolix.base.net.target.ServerTarget;
import ch.nolix.baseapi.net.netcatalog.PortCatalog;
import ch.nolix.baseapi.net.netproperty.SecurityMode;
import ch.nolix.baseapi.net.ssl.ISslCertificate;
import ch.nolix.baseapi.net.target.IServerTarget;
import ch.nolix.systemapi.application.main.IApplication;

/**
 * @author Silvan Wyss
 */
public final class SslServer extends AbstractServer<SslServer> {
  private static final SecurityMode SECURITY_MODE_FOR_CONNECTIONS = SecurityMode.SSL;

  private final ch.nolix.base.net.level3server.SslServer internalWebSocketServer;

  private final String domain;

  private final int port;

  private SslServer(final int port, final String domain, final ISslCertificate paramSSLCertificate) {
    final var htmlPage = SslServerPage.forDomainAndPort(domain, port);
    final var htmlPageAsString = htmlPage.toString();

    internalWebSocketServer = ch.nolix.base.net.level3server.SslServer.forPortAndHtmlPageAndSSLCertificate(port,
      htmlPageAsString, paramSSLCertificate);

    this.domain = domain;
    this.port = port;

    createCloseDependencyTo(internalWebSocketServer);
  }

  public static SslServer forHttpsPortAndDomainAndSSLCertificateFromNolixConfiguration() {
    final var domain = NolixConfigurationSslCertificateReader.getDefaultDomainFromLocalNolixConfiguration();

    final var paramSSLCertificate = //
    NolixConfigurationSslCertificateReader.getDefaultSSLCertificatefromLocalNolixConfiguration();

    return new SslServer(PortCatalog.HTTPS, domain, paramSSLCertificate);
  }

  public static SslServer forHttpsPortAndDomainAndSSLCertificateFromNolixConfiguration(
    final String domain) {
    final var paramSSLCertificate = //
    NolixConfigurationSslCertificateReader.getDefaultSSLCertificatefromLocalNolixConfiguration();

    return new SslServer(PortCatalog.HTTPS, domain, paramSSLCertificate);
  }

  public static SslServer forPortAndDomainAndSSLCertificateFromNolixConfiguration(
    final int port,
    final String domain) {
    final var paramSSLCertificate = //
    NolixConfigurationSslCertificateReader.getDefaultSSLCertificatefromLocalNolixConfiguration();

    return new SslServer(port, domain, paramSSLCertificate);
  }

  /**
   * {@inheritDoc}
   */
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

  /**
   * {@inheritDoc}
   */
  @Override
  protected void noteAddedApplication(final Application<?, ?> application) {
    internalWebSocketServer.addSlot(Slot.withNameAndParentServer(application.getUrlInstanceName(), this));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void noteAddedDefaultApplication(final Application<?, ?> defaultApplication) {
    internalWebSocketServer.addDefaultSlot(Slot.withNameAndParentServer(defaultApplication.getUrlInstanceName(), this));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void noteRemovedApplication(final IApplication<?, ?> application) {
    internalWebSocketServer.removeSlotByName(application.getUrlInstanceName());
  }
}
