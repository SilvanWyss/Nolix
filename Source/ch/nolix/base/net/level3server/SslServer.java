/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.level3server;

import ch.nolix.base.net.ssl.NolixConfigurationSslCertificateReader;
import ch.nolix.baseapi.net.level3server.ISlot;
import ch.nolix.baseapi.net.securityproperty.SecurityMode;
import ch.nolix.baseapi.net.ssl.ISslCertificate;

/**
 * @author Silvan Wyss
 */
public final class SslServer extends AbstractServer {
  private final ch.nolix.base.net.level2server.SslServer internalWebSocketServer;

  private SslServer(final int port, final String HtmlPage, final ISslCertificate paramSSLCertificate) {
    internalWebSocketServer = //
    ch.nolix.base.net.level2server.SslServer.forPortAndHtmlPageAndSslCertificate(port, HtmlPage, paramSSLCertificate);

    createCloseDependencyTo(internalWebSocketServer);
  }

  public static SslServer forPortAndHtmlPageAndSSLCertificate(
    final int port,
    final String htmlPage,
    final ISslCertificate sslCertificate) {
    return new SslServer(port, htmlPage, sslCertificate);
  }

  public static SslServer forPortAndHtmlPageAndSSLCertificateFromNolixConfiguration(
    final int port,
    final String htmlPage) {
    final var sslCertificate = //
    NolixConfigurationSslCertificateReader.getDefaultSSLCertificatefromLocalNolixConfiguration();

    return new SslServer(port, htmlPage, sslCertificate);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public SecurityMode getSecurityMode() {
    return SecurityMode.SSL;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void noteAddedDefaultSlot(final ISlot defaultSlot) {
    internalWebSocketServer.addDefaultSlot(Level2Slot.withNameAndParentServer(defaultSlot.getName(), this));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void noteAddedSlot(final ISlot slot) {
    internalWebSocketServer.addSlot(Level2Slot.withNameAndParentServer(slot.getName(), this));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void noteRemovedSlot(final ISlot slot) {
    internalWebSocketServer.removeSlotByName(slot.getName());
  }
}
