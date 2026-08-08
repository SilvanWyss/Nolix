/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.senderandreplierserver;

import ch.nolix.base.net.ssl.NolixConfigurationSslCertificateReader;
import ch.nolix.baseapi.net.netproperty.SecurityMode;
import ch.nolix.baseapi.net.senderandreplierserver.Slot;
import ch.nolix.baseapi.net.ssl.ISslCertificate;

/**
 * @author Silvan Wyss
 */
public final class SslServer extends AbstractServer {
  private final ch.nolix.base.net.senderandreceiverserver.SslServer internalWebSocketServer;

  private SslServer(final int port, final String HtmlPage, final ISslCertificate paramSSLCertificate) {
    internalWebSocketServer = //
    ch.nolix.base.net.senderandreceiverserver.SslServer.forPortAndHtmlPageAndSSLCertificate(
      port,
      HtmlPage,
      paramSSLCertificate);

    createCloseDependencyTo(internalWebSocketServer);
  }

  public static SslServer forPortAndHtmlPageAndSslCertificate(
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
  protected void noteAddedDefaultSlot(final Slot defaultSlot) {
    internalWebSocketServer.addDefaultSlot(SenderAndReceiverSlot.withNameAndParentServer(defaultSlot.getName(), this));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void noteAddedSlot(final Slot slot) {
    internalWebSocketServer.addSlot(SenderAndReceiverSlot.withNameAndParentServer(slot.getName(), this));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void noteRemovedSlot(final Slot slot) {
    internalWebSocketServer.removeSlotByName(slot.getName());
  }
}
