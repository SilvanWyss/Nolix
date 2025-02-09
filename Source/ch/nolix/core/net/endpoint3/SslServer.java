package ch.nolix.core.net.endpoint3;

import ch.nolix.core.net.ssl.NolixConfigurationSSLCertificateReader;
import ch.nolix.coreapi.netapi.endpoint3api.ISlot;
import ch.nolix.coreapi.netapi.securityproperty.SecurityMode;
import ch.nolix.coreapi.netapi.sslapi.ISslCertificate;

public final class SslServer extends AbstractServer {

  private static final NolixConfigurationSSLCertificateReader NOLIX_CONFIUGEATION_SSL_CERTIFICATE_READER = //
  new NolixConfigurationSSLCertificateReader();

  private final ch.nolix.core.net.endpoint2.SslServer internalWebSocketServer;

  private SslServer(final int port, final String HtmlPage, final ISslCertificate paramSSLCertificate) {

    internalWebSocketServer = new ch.nolix.core.net.endpoint2.SslServer(port, HtmlPage, paramSSLCertificate);

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
    NOLIX_CONFIUGEATION_SSL_CERTIFICATE_READER.getDefaultSSLCertificatefromLocalNolixConfiguration();

    return new SslServer(port, htmlPage, sslCertificate);
  }

  @Override
  public SecurityMode getSecurityMode() {
    return SecurityMode.SSL;
  }

  @Override
  protected void noteAddedDefaultSlot(final ISlot defaultSlot) {
    internalWebSocketServer.addDefaultSlot(new ServerSlot(defaultSlot.getName(), this));
  }

  @Override
  protected void noteAddedSlot(final ISlot slot) {
    internalWebSocketServer.addSlot(new ServerSlot(slot.getName(), this));
  }

  @Override
  protected void noteRemovedSlot(final ISlot slot) {
    internalWebSocketServer.removeSlotByName(slot.getName());
  }
}
