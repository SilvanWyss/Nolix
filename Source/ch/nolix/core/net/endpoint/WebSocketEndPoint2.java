//package declaration
package ch.nolix.core.net.endpoint;

//ToolTallNate imports
import org.java_websocket.WebSocket;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.netapi.netproperty.ConnectionType;
import ch.nolix.coreapi.netapi.netproperty.PeerType;
import ch.nolix.coreapi.programcontrolapi.processproperty.TargetInfoState;

//class
final class WebSocketEndPoint2 extends NetEndPoint {
	
	//attribute
	private final WebSocket webSocket;
	
	//constructor
	public WebSocketEndPoint2(final WebSocket webSocket) {
		
		super(TargetInfoState.WAITS_TO_TARGET_INFO);
		
		GlobalValidator.assertThat(webSocket).thatIsNamed(WebSocket.class).isNotNull();
		
		this.webSocket = webSocket;
	}
	
	//method
	@Override
	public ConnectionType getConnectionType() {
		return ConnectionType.WEB_SOCKET;
	}
	
	//method
	@Override
	public PeerType getPeerType() {
		return PeerType.BACKEND;
	}
	
	//method
	@Override
	public void noteClose() {
		webSocket.close();
	}
	
	//method
	@Override
	protected void sendRawMessage(final String rawMessage) {
		webSocket.send(rawMessage);
	}
	
	//method
	boolean internalHasWebSocket(final WebSocket webSocket) {
		return (this.webSocket == webSocket);
	}
}
