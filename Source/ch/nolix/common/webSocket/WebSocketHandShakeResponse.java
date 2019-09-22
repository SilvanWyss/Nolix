//package declaration
package ch.nolix.common.webSocket;

//Java imports
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

//own import
import ch.nolix.common.validator.Validator;

//class
public final class WebSocketHandShakeResponse {
	
	//constants
	public static final String WEB_SOCKET_GUID = "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
	public static final String SEC_WEBSOCKET_ACCEPT_HEADER = "Sec-WebSocket-Accept";
	
	//attributes
	private final String secWebSocketKey;
	private final String secWebSocketAccept;
	
	//constructor
	public WebSocketHandShakeResponse(final String secWebSocketKey) {
				
		Validator.suppose(secWebSocketKey).thatIsNamed("sec web socket key").isNotNull();
		
		this.secWebSocketKey = secWebSocketKey;
		
		final var secWebSocketKeyAndWebSocketGUID = secWebSocketKey + WEB_SOCKET_GUID;	
		try {
			final var messageDigest = MessageDigest.getInstance("SHA-1");
			var bytes = messageDigest.digest(secWebSocketKeyAndWebSocketGUID.getBytes(StandardCharsets.UTF_8));
	        secWebSocketAccept = Base64.getEncoder().encodeToString(bytes);
		}
		catch (final NoSuchAlgorithmException noSuchAlgorithmException) {
			throw new RuntimeException(noSuchAlgorithmException);
		}
	}
	
	//method
	public String getSecWebSocketAccept() {
		return secWebSocketAccept;
	}
		
	//method
	public String getSecWebSocketKey() {
		return secWebSocketKey;
	}
	
	//method
	public String toString() {
		return
		"HTTP/1.1 101 Switching Protocols\r\n"
		+ "Upgrade: websocket\r\n"
		+ "Connection: Upgrade\r\n"
		+ SEC_WEBSOCKET_ACCEPT_HEADER + ": " + secWebSocketAccept + "\r\n"
		+ "\r\n";
	}
}
