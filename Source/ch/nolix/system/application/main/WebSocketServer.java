//package declaration
package ch.nolix.system.application.main;

//own imports
import ch.nolix.core.net.constant.PortCatalogue;
import ch.nolix.core.net.tls.NolixConfigurationSSLCertificateReader;
import ch.nolix.coreapi.netapi.tlsapi.ISSLCertificate;

//class
public final class WebSocketServer extends BaseServer {
	
	//constant
	public static final int DEFAULT_PORT = PortCatalogue.HTTPS_PORT;
	
	//constant
	private static final NolixConfigurationSSLCertificateReader NOLIX_CONFIUGEATION_SSL_CERTIFICATE_READER =
	NolixConfigurationSSLCertificateReader.INSTANCE;
	
	//static method
	public static WebSocketServer forHttpsPortAndDomainAndSSLCertificateFromNolixConfiguration() {
		
		final var domain = NOLIX_CONFIUGEATION_SSL_CERTIFICATE_READER.getDefaultDomainFromLocalNolixConfiguration();
		
		final var paramSSLCertificate =
		NOLIX_CONFIUGEATION_SSL_CERTIFICATE_READER.getDefaultSSLCertificatefromLocalNolixConfiguration();
		
		return new WebSocketServer(PortCatalogue.HTTPS_PORT, domain, paramSSLCertificate);
	}
	
	//static method
	public static WebSocketServer forDefaultPortAndDomainAndSSLCertificateFromNolixConfiguration(
		final String domain
	) {
		
		final var paramSSLCertificate =
		NOLIX_CONFIUGEATION_SSL_CERTIFICATE_READER.getDefaultSSLCertificatefromLocalNolixConfiguration();
		
		return new WebSocketServer(DEFAULT_PORT, domain, paramSSLCertificate);
	}
	
	//static method
	public static WebSocketServer forPortAndDomainAndSSLCertificateFromNolixConfiguration(
		final int port,
		final String domain
	) {
		
		final var paramSSLCertificate =
		NOLIX_CONFIUGEATION_SSL_CERTIFICATE_READER.getDefaultSSLCertificatefromLocalNolixConfiguration();
		
		return new WebSocketServer(port, domain, paramSSLCertificate);
	}
	
	//attribute
	private final ch.nolix.core.net.endpoint3.WebSocketServer internalWebSocketServer;
	
	//constructor
	public WebSocketServer(final int port, final String domain, final ISSLCertificate paramSSLCertificate) {
		
		final var localHTMLPage = new WebSocketServerHTMLPage(domain, port);
		final var localHTMLPageAsString = localHTMLPage.toString();
		
		internalWebSocketServer =
		new ch.nolix.core.net.endpoint3.WebSocketServer(port, localHTMLPageAsString, paramSSLCertificate);
		
		createCloseDependencyTo(internalWebSocketServer);
	}
	
	//method
	@Override
	protected void noteAddedApplication(final Application<?, ?> application) {
		internalWebSocketServer.addSlot(new ServerEndPointTaker(application.getInstanceName(), this));
	}
	
	//method
	@Override
	protected void noteAddedDefaultApplication(final Application<?, ?> defaultApplication) {
		internalWebSocketServer.addDefaultSlot(new ServerEndPointTaker(defaultApplication.getInstanceName(), this));
	}
}
