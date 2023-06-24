//package declaration
package ch.nolix.core.net.endpoint3;

//own imports
import ch.nolix.core.net.tls.NolixConfigurationSSLCertificateReader;
import ch.nolix.coreapi.netapi.endpoint3api.ISlot;
import ch.nolix.coreapi.netapi.tlsapi.ISSLCertificate;

//class
public final class SecureServer extends BaseServer {
	
	//constant
	private static final NolixConfigurationSSLCertificateReader NOLIX_CONFIUGEATION_SSL_CERTIFICATE_READER =
	NolixConfigurationSSLCertificateReader.INSTANCE;
	
	//static method
	public SecureServer forPortAndHtmlPageAndSSLCertificateFromNolixConfiguration(
		final int port,
		final String htmlPage
	) {
		
		final var paramSSLCertificate =
		NOLIX_CONFIUGEATION_SSL_CERTIFICATE_READER.getDefaultSSLCertificatefromLocalNolixConfiguration();
		
		return new SecureServer(port, htmlPage, paramSSLCertificate);
	}
	
	//attribute
	private final ch.nolix.core.net.endpoint2.SecureServer internalWebSocketServer;
			
	//constructor
	public SecureServer(final int port, final String HTMLPage, final ISSLCertificate paramSSLCertificate) {
		
		internalWebSocketServer = new ch.nolix.core.net.endpoint2.SecureServer(port, HTMLPage, paramSSLCertificate);
		
		createCloseDependencyTo(internalWebSocketServer);
	}
	
	//method
	@Override
	protected void noteAddedDefaultSlot(final ISlot defaultSlot) {
		internalWebSocketServer.addDefaultSlot(new ServerEndPointTaker(defaultSlot.getName(), this));
	}
	
	//method
	@Override
	protected void noteAddedSlot(final ISlot slot) {
		internalWebSocketServer.addSlot(new ServerEndPointTaker(slot.getName(), this));
	}
}

