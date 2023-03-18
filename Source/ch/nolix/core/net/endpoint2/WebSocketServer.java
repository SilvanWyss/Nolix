//package declaration
package ch.nolix.core.net.endpoint2;

//own imports
import ch.nolix.coreapi.netapi.endpoint2api.ISlot;
import ch.nolix.coreapi.netapi.tlsapi.ISSLCertificate;

//class
public final class WebSocketServer extends BaseServer {
	
	//attribute
	private final ch.nolix.core.net.endpoint.WebSocketServer internalWebSocketServer;
	
	//constructor
	public WebSocketServer(final int port, final ISSLCertificate paramSSLCertificate) {
		
		internalWebSocketServer = new ch.nolix.core.net.endpoint.WebSocketServer(port, paramSSLCertificate);
		
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
