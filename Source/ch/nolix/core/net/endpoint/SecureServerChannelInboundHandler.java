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
	
	//attribute
	private final SecureServer parentWebSocketServer;
	
	//optional attribute
	private SecureServerEndPoint parentWebSocketServerEndPoint;
	
	//constructor
	public SecureServerChannelInboundHandler(final SecureServer parentWebSocketServer) {
		
		GlobalValidator.assertThat(parentWebSocketServer).thatIsNamed("parent web-socket server").isNotNull();
		
		this.parentWebSocketServer = parentWebSocketServer;
	}
	
	//method
	@Override
	protected void channelRead0(final ChannelHandlerContext ctx, final WebSocketFrame frame) throws Exception {
		
		if (parentWebSocketServerEndPoint == null) {
			parentWebSocketServerEndPoint = new SecureServerEndPoint(ctx);
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
