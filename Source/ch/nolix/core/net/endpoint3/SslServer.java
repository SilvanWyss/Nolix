//package declaration
package ch.nolix.core.net.endpoint3;

import ch.nolix.core.net.ssl.NolixConfigurationSSLCertificateReader;
import ch.nolix.coreapi.netapi.endpoint3api.ISlot;
import ch.nolix.coreapi.netapi.securityproperty.SecurityMode;
import ch.nolix.coreapi.netapi.sslapi.ISSLCertificate;

//class
public final class SslServer extends BaseServer {

  //constant
  private static final NolixConfigurationSSLCertificateReader NOLIX_CONFIUGEATION_SSL_CERTIFICATE_READER = //
  //
  new NolixConfigurationSSLCertificateReader();

  //attribute
  private final ch.nolix.core.net.endpoint2.SslServer internalWebSocketServer;

  //constructor
  public SslServer(final int port, final String HtmlPage, final ISSLCertificate paramSSLCertificate) {

    internalWebSocketServer = new ch.nolix.core.net.endpoint2.SslServer(port, HtmlPage, paramSSLCertificate);

    createCloseDependencyTo(internalWebSocketServer);
  }

  //static method
  public SslServer forPortAndHtmlPageAndSSLCertificateFromNolixConfiguration(
    final int port,
    final String htmlPage) {

    final var paramSSLCertificate = NOLIX_CONFIUGEATION_SSL_CERTIFICATE_READER
      .getDefaultSSLCertificatefromLocalNolixConfiguration();

    return new SslServer(port, htmlPage, paramSSLCertificate);
  }

  //method
  @Override
  public SecurityMode getSecurityLevel() {
    return SecurityMode.SSL;
  }

  //method
  @Override
  protected void noteAddedDefaultSlot(final ISlot defaultSlot) {
    internalWebSocketServer.addDefaultSlot(new ServerSlot(defaultSlot.getName(), this));
  }

  //method
  @Override
  protected void noteAddedSlot(final ISlot slot) {
    internalWebSocketServer.addSlot(new ServerSlot(slot.getName(), this));
  }

  //method
  @Override
  protected void noteRemovedSlot(final ISlot slot) {
    internalWebSocketServer.removeSlotByName(slot.getName());
  }
}
