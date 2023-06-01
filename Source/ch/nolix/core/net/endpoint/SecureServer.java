//package declaration
package ch.nolix.core.net.endpoint;

//own imports
import ch.nolix.core.net.tls.NolixConfigurationSSLCertificateReader;
import ch.nolix.coreapi.netapi.tlsapi.ISSLCertificate;

//class
public final class SecureServer extends BaseServer {
	
	//constant
	public static final String DEFAULT_HTML_PAGE =
	"""
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
	private static final NolixConfigurationSSLCertificateReader NOLIX_CONFIUGEATION_SSL_CERTIFICATE_READER =
	NolixConfigurationSSLCertificateReader.INSTANCE;
	
	//static method
	public static SecureServer forPortAndHTMLPageAndSSLCertificateFromNolixConfiguration(
		final int port,
		final String paramHTMLPage
	) {
		
		final var paramSSLCertificate =
		NOLIX_CONFIUGEATION_SSL_CERTIFICATE_READER.getDefaultSSLCertificatefromLocalNolixConfiguration();
		
		return new SecureServer(port, paramHTMLPage, paramSSLCertificate);
	}
	
	//constructor
	public SecureServer(final int port, final ISSLCertificate paramSSLCertificate) {
		this(port, DEFAULT_HTML_PAGE, paramSSLCertificate);
	}
	
	//constructor
	public SecureServer(final int port, final String paramHTMLPage, final ISSLCertificate paramSSLCertificate) {
		new SecureServerWorker(this, port, paramHTMLPage, paramSSLCertificate);
	}
	
	//method
	@Override
	public void noteClose() {
		//TODO: Implement.
	}
}