/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.level1server;

import ch.nolix.base.net.ssl.NolixConfigurationSslCertificateReader;
import ch.nolix.baseapi.net.netproperty.SecurityMode;
import ch.nolix.baseapi.net.ssl.ISslCertificate;

/**
 * @author Silvan Wyss
 */
public final class SslServer extends AbstractServer {
  public static final String DEFAULT_HTML_PAGE = """
  <!DOCTYPE html>
  <html>
  <head>
  <title>Nolix</title>
  <style>*{font-family: Calibri;}</style>
  </head>
  <body>
  <h1>Nolix</h1>
  <p>The requested server does not support web clients.</p>
  </body>
  </html>
  """;

  private final SslServerWorker sslServerWorker;

  private SslServer(final int port, final String htmlPage, final ISslCertificate paramSSLCertificate) {
    sslServerWorker = //
    SslServerWorker.forWebSocketServerAndPortAndHtmlPageAndSslCertificate(this, port, htmlPage, paramSSLCertificate);
  }

  public static SslServer forPortAndHtmlPageAndSSLCertificate(
    final int port,
    final String htmlPage,
    final ISslCertificate paramSSLCertificate) {
    return new SslServer(port, htmlPage, paramSSLCertificate);
  }

  public static SslServer forPortAndHtmlPageAndSSLCertificateFromNolixConfiguration(
    final int port,
    final String htmlPage) {
    final var paramSSLCertificate = //
    NolixConfigurationSslCertificateReader.getDefaultSSLCertificatefromLocalNolixConfiguration();

    return new SslServer(port, htmlPage, paramSSLCertificate);
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
  public void noteClose() {
    sslServerWorker.internalStop();
  }
}
