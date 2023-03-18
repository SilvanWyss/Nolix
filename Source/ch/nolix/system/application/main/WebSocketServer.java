//package declaration
package ch.nolix.system.application.main;

//own imports
import ch.nolix.coreapi.netapi.tlsapi.ISSLCertificate;

//class
public final class WebSocketServer extends BaseServer {
	
	//attribute
	private final ch.nolix.core.net.endpoint3.WebSocketServer internalWebSocketServer;
	
	//constructor
	public WebSocketServer(final int port, final ISSLCertificate paramSSLCertificate) {
		
		internalWebSocketServer = new ch.nolix.core.net.endpoint3.WebSocketServer(port, paramSSLCertificate);
		
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
