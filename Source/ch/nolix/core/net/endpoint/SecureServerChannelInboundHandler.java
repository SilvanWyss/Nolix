//package declaration
package ch.nolix.core.net.endpoint;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
//Netty imports
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

//class
final class SecureServerChannelInboundHandler extends SimpleChannelInboundHandler<WebSocketFrame> {

  // attribute
  private final SecureServer parentWebSocketServer;

  // optional attribute
  private SecureServerEndPoint parentWebSocketServerEndPoint;

  // constructor
  public SecureServerChannelInboundHandler(final SecureServer parentWebSocketServer) {

    GlobalValidator.assertThat(parentWebSocketServer).thatIsNamed("parent web-socket server").isNotNull();

    this.parentWebSocketServer = parentWebSocketServer;
  }

  // method
  /**
   * Is triggered when the communication is stopped. For example when the client
   * closes the connections. Closes the parent {@link SecureServerEndPoint} of the
   * current {@link SecureServerChannelInboundHandler}.
   */
  @Override
  public void channelInactive(final ChannelHandlerContext channelHandlerContext) throws Exception {
    parentWebSocketServerEndPoint.close();
  }

  // method
  @Override
  protected void channelRead0(
      final ChannelHandlerContext channelHandlerContext,
      final WebSocketFrame webSocketFrame) throws Exception {
    if (parentWebSocketServerEndPoint == null) {

      parentWebSocketServerEndPoint = new SecureServerEndPoint(channelHandlerContext);

      // The end point must receive the current message to know its content before it
      // is added to the server.
      final var rawMessage = ((TextWebSocketFrame) webSocketFrame).text();
      parentWebSocketServerEndPoint.receiveRawMessage(rawMessage);

      parentWebSocketServer.internalTakeBackendEndPoint(parentWebSocketServerEndPoint);
    } else if (webSocketFrame instanceof TextWebSocketFrame textWebSocketFrame) {

      final var rawMessage = textWebSocketFrame.text();

      parentWebSocketServerEndPoint.receiveRawMessageInBackground(rawMessage);
    } else {
      throw InvalidArgumentException.forArgument(webSocketFrame);
    }
  }
}
