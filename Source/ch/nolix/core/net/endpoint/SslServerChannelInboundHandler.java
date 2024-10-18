package ch.nolix.core.net.endpoint;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

final class SslServerChannelInboundHandler extends SimpleChannelInboundHandler<WebSocketFrame> {

  private final SslServer parentWebSocketServer;

  private SslServerEndPoint parentWebSocketServerEndPoint;

  public SslServerChannelInboundHandler(final SslServer parentWebSocketServer) {

    GlobalValidator.assertThat(parentWebSocketServer).thatIsNamed("parent web-socket server").isNotNull();

    this.parentWebSocketServer = parentWebSocketServer;
  }

  /**
   * Is triggered when the communication is stopped. For example when the client
   * closes the connections. Closes the parent {@link SslServerEndPoint} of the
   * current {@link SslServerChannelInboundHandler}.
   */
  @Override
  public void channelInactive(final ChannelHandlerContext channelHandlerContext) throws Exception {
    parentWebSocketServerEndPoint.close();
  }

  @Override
  protected void channelRead0(
    final ChannelHandlerContext channelHandlerContext,
    final WebSocketFrame webSocketFrame)
  throws Exception {
    if (parentWebSocketServerEndPoint == null) {

      parentWebSocketServerEndPoint = new SslServerEndPoint(channelHandlerContext);

      //The end point must receive the current message to know its content before it
      //is added to the server.
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
