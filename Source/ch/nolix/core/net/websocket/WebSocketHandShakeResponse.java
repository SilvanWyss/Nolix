package ch.nolix.core.net.websocket;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import ch.nolix.core.errorcontrol.generalexception.WrapperException;
import ch.nolix.core.errorcontrol.validator.Validator;

public final class WebSocketHandShakeResponse {

  public static final String WEB_SOCKET_GUID = "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";

  public static final String SEC_WEBSOCKET_ACCEPT_HEADER = "Sec-WebSocket-Accept";

  public static final String SEC_WEBSOCKET_PROTOCOL_HEADER = "Sec-WebSocket-Protocol";

  private final String secWebSocketKey;

  private final String secWebSocketAccept;

  public WebSocketHandShakeResponse(final String secWebSocketKey) {

    Validator.assertThat(secWebSocketKey).thatIsNamed("sec web socket key").isNotNull();

    this.secWebSocketKey = secWebSocketKey;

    final var secWebSocketKeyAndWebSocketGUID = secWebSocketKey + WEB_SOCKET_GUID;
    try {
      final var messageDigest = MessageDigest.getInstance("SHA-1");
      var bytes = messageDigest.digest(secWebSocketKeyAndWebSocketGUID.getBytes(StandardCharsets.UTF_8));
      secWebSocketAccept = Base64.getEncoder().encodeToString(bytes);
    } catch (final NoSuchAlgorithmException noSuchAlgorithmException) {
      throw WrapperException.forError(noSuchAlgorithmException);
    }
  }

  public String getSecWebSocketAccept() {
    return secWebSocketAccept;
  }

  public String getSecWebSocketKey() {
    return secWebSocketKey;
  }

  @Override
  public String toString() {
    return "HTTP/1.1 101 Switching Protocols\r\n"
    + "Upgrade: websocket\r\n"
    + "Connection: Upgrade\r\n"
    + SEC_WEBSOCKET_ACCEPT_HEADER + ": " + secWebSocketAccept + "\r\n"
    + "\r\n";
  }
}
