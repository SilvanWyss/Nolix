//package declaration
package ch.nolix.core.net.endpoint;

//Java imports
import java.net.InetSocketAddress;

//ToolTallNate imports
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;

//class
final class WebSocketServerInternalServer extends org.java_websocket.server.WebSocketServer {
	
	//attribute
	private final WebSocketServer parentWebSocketServer;
	
	//multi-attribute
	private final LinkedList<WebSocketEndPoint2> webSocketEndPoints = new LinkedList<>();
	
	//constructor
	public WebSocketServerInternalServer(final WebSocketServer parentWebSocketServer, final int port) {
		
		super(new InetSocketAddress(port));
		
		GlobalValidator.assertThat(parentWebSocketServer).thatIsNamed("parent web socket server").isNotNull();
		
		this.parentWebSocketServer = parentWebSocketServer;
		
		start();
	}
	
	//method
	@Override
	public void onClose(WebSocket arg0, int arg1, String arg2, boolean arg3) {
		getRefWebsocketEndPoint(arg0).close();
	}
	
	//method
	@Override
	public void onError(WebSocket arg0, Exception arg1) {
		getRefWebsocketEndPoint(arg0).close();
	}
	
	//method
	@Override
	public void onMessage(WebSocket arg0, String arg1) {
		getRefWebsocketEndPoint(arg0).receiveRawMessageInBackground(arg1);
	}
	
	//method
	@Override
	public void onOpen(WebSocket arg0, ClientHandshake arg1) {
		
		final var webSocketEndPoint = new WebSocketEndPoint2(arg0);
		
		webSocketEndPoints.addAtBegin(webSocketEndPoint);
		
		parentWebSocketServer.internalTakeBackendEndPoint(webSocketEndPoint);
	}
	
	//method
	@Override
	public void onStart() {
		//Does nothing.
	}
	
	//method
	private WebSocketEndPoint2 getRefWebsocketEndPoint(final WebSocket webSocket) {
		return webSocketEndPoints.getRefFirst(ep -> ep.internalHasWebSocket(webSocket));
	}
}
