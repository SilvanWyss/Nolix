//package declaration
package ch.nolix.core.net.endpoint;

//own imports
import ch.nolix.coreapi.netapi.tlsapi.ISSLCertificate;

//class
public final class WebSocketServer extends BaseServer {
	
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
	
	//constructor
	public WebSocketServer(final int port, final ISSLCertificate paramSSLCertificate) {
		this(port, DEFAULT_HTML_PAGE, paramSSLCertificate);
	}
	
	//constructor
	public WebSocketServer(final int port, final String paramHTMLPage, final ISSLCertificate paramSSLCertificate) {
		new WebSocketServerWorker(this, port, paramHTMLPage, paramSSLCertificate);
	}
	
	//method
	@Override
	public void noteClose() {
		//TODO: Implement.
	}
}
