//package declaration
package ch.nolix.core.net.endpoint;

//Netty imports
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.netapi.netproperty.ConnectionType;
import ch.nolix.coreapi.netapi.netproperty.PeerType;
import ch.nolix.coreapi.programcontrolapi.processproperty.TargetInfoState;

//class
final class WebSocketServerEndPoint extends NetEndPoint {
	
	private final ChannelHandlerContext channelHandlerContext;
	
	//constructor
	public WebSocketServerEndPoint(final ChannelHandlerContext channelHandlerContext) {
		
		super(TargetInfoState.WAITS_TO_TARGET_INFO);
		
		GlobalValidator.assertThat(channelHandlerContext).thatIsNamed(ChannelHandlerContext.class).isNotNull();
		
		this.channelHandlerContext = channelHandlerContext;
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
		//TODO: Implement.
	}
	
	//method
	@Override
	protected void sendRawMessage(final String rawMessage) {
		channelHandlerContext.channel().writeAndFlush(new TextWebSocketFrame(rawMessage));
	}
}
