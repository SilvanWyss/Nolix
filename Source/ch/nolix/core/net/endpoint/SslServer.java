//package declaration
package ch.nolix.core.net.endpoint;

//own imports
import ch.nolix.core.net.tls.NolixConfigurationSSLCertificateReader;
import ch.nolix.coreapi.netapi.securityapi.SecurityMode;
import ch.nolix.coreapi.netapi.tlsapi.ISSLCertificate;

//class
public final class SslServer extends BaseServer {

  //constant
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

  //constant
  private static final NolixConfigurationSSLCertificateReader NOLIX_CONFIUGEATION_SSL_CERTIFICATE_READER = //
  new NolixConfigurationSSLCertificateReader();

  //constructor
  public SslServer(final int port, final ISSLCertificate paramSSLCertificate) {
    this(port, DEFAULT_HTML_PAGE, paramSSLCertificate);
  }

  //constructor
  public SslServer(final int port, final String htmlPage, final ISSLCertificate paramSSLCertificate) {
    new SslServerWorker(this, port, htmlPage, paramSSLCertificate);
  }

  //static method
  public static SslServer forPortAndHtmlPageAndSSLCertificateFromNolixConfiguration(
    final int port,
    final String htmlPage) {

    final var paramSSLCertificate = NOLIX_CONFIUGEATION_SSL_CERTIFICATE_READER
      .getDefaultSSLCertificatefromLocalNolixConfiguration();

    return new SslServer(port, htmlPage, paramSSLCertificate);
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public SecurityMode getSecurityMode() {
    return SecurityMode.SSL;
  }

  //method
  @Override
  public void noteClose() {
    //TODO: Implement.
  }
}
