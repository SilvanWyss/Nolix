/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.senderandreceiverserver;

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

  private SslServer(final int port, final String htmlPage, final ISslCertificate paramSslCertificate) {
    sslServerWorker = //
    SslServerWorker.forWebSocketServerAndPortAndHtmlPageAndSslCertificate(this, port, htmlPage, paramSslCertificate);
  }

  /**
   * @param port
   * @param htmlPage
   * @param paramSslCertificate
   * @return a new {@link SslServer} that listens on the given port and uses the
   *         given htmlPage and paramSslCertificate
   */
  public static SslServer forPortAndHtmlPageAndSslCertificate(
    final int port,
    final String htmlPage,
    final ISslCertificate paramSslCertificate) {
    return new SslServer(port, htmlPage, paramSslCertificate);
  }

  /**
   * @param port
   * @param htmlPage
   * @return a new {@link SslServer} that listens on the given port and uses the
   *         given htmlPage and the default SSL certificate from the local Nolix
   *         configuration
   */
  public static SslServer forPortAndHtmlPageAndSslCertificateFromNolixConfiguration(
    final int port,
    final String htmlPage) {
    final var paramSslCertificate = //
    NolixConfigurationSslCertificateReader.getDefaultSslCertificateFromLocalNolixConfiguration();

    return new SslServer(port, htmlPage, paramSslCertificate);
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
