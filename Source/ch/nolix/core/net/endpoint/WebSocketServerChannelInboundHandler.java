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
final class WebSocketServerChannelInboundHandler extends SimpleChannelInboundHandler<WebSocketFrame> {
	
	//attribute
	private final WebSocketServer parentWebSocketServer;
	
	//optional attribute
	private WebSocketServerEndPoint parentWebSocketServerEndPoint;
	
	//constructor
	public WebSocketServerChannelInboundHandler(final WebSocketServer parentWebSocketServer) {
		
		GlobalValidator.assertThat(parentWebSocketServer).thatIsNamed("parent web-socket server").isNotNull();
		
		this.parentWebSocketServer = parentWebSocketServer;
	}
	
	//method
	@Override
	protected void channelRead0(final ChannelHandlerContext ctx, final WebSocketFrame frame) throws Exception {
		
		if (parentWebSocketServerEndPoint == null) {
			parentWebSocketServerEndPoint = new WebSocketServerEndPoint(ctx);
			parentWebSocketServer.internalTakeBackendEndPoint(parentWebSocketServerEndPoint);
		}
		
		if (frame instanceof TextWebSocketFrame) {
			String rawMessage = ((TextWebSocketFrame) frame).text();
			parentWebSocketServerEndPoint.receiveRawMessageInBackground(rawMessage);
		} else {
			throw InvalidArgumentException.forArgument(frame);
		}
	}
}
