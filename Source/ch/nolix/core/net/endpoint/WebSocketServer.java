//package declaration
package ch.nolix.core.net.endpoint;

//own imports
import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.coreapi.netapi.tlsapi.ISSLCertificate;

//class
public final class WebSocketServer extends BaseServer {
	
	//attribute
	private final WebSocketServerInternalServer internalWebSocketServer;
	
	//constructor
	public WebSocketServer(final int port, final ISSLCertificate paramSSLCertificate) {
		internalWebSocketServer = new WebSocketServerInternalServer(this, port);
	}
	
	//method
	@Override
	public void noteClose() {
		try {
			internalWebSocketServer.stop();
		}
		catch (final InterruptedException interruptedException) { //NOSONAR: An Exception will be re-thrown.
			throw WrapperException.forError(interruptedException);
		}
	}
}
