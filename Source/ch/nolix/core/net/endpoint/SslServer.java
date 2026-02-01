/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.core.net.endpoint;

import ch.nolix.core.net.ssl.NolixConfigurationSslCertificateReader;
import ch.nolix.coreapi.net.securityproperty.SecurityMode;
import ch.nolix.coreapi.net.ssl.ISslCertificate;

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

  private static final NolixConfigurationSslCertificateReader NOLIX_CONFIUGEATION_SSL_CERTIFICATE_READER = //
  new NolixConfigurationSslCertificateReader();

  private final SslServerWorker sslServerWorker;

  public SslServer(final int port, final ISslCertificate paramSSLCertificate) {
    this(port, DEFAULT_HTML_PAGE, paramSSLCertificate);
  }

  public SslServer(final int port, final String htmlPage, final ISslCertificate paramSSLCertificate) {
    sslServerWorker = new SslServerWorker(this, port, htmlPage, paramSSLCertificate);
  }

  public static SslServer forPortAndHtmlPageAndSSLCertificateFromNolixConfiguration(
    final int port,
    final String htmlPage) {
    final var paramSSLCertificate = NOLIX_CONFIUGEATION_SSL_CERTIFICATE_READER
      .getDefaultSSLCertificatefromLocalNolixConfiguration();

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
