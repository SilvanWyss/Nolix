//package declaration
package ch.nolix.core.net.endpoint;

//Netty imports
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.ssl.SslContext;

//class
final class SecureServerInitializer extends ChannelInitializer<SocketChannel> {
	
	private static final String WEBSOCKET_PATH = "/websocket";
	
	//attribute
	private final SecureServer parentWebSocketServer;
	
	//attribute
	private final String mHTMLPage;
	
	//attribute
	private final SslContext sslCtx;
	
	public SecureServerInitializer(SecureServer parentWebSocketServer, String paramHTMLPage, SslContext sslCtx) {
		this.parentWebSocketServer = parentWebSocketServer;
		mHTMLPage = paramHTMLPage;
		this.sslCtx = sslCtx;
	}
	
	@Override
	public void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		if (sslCtx != null) {
			pipeline.addLast(sslCtx.newHandler(ch.alloc()));
		}
		pipeline.addLast(new HttpServerCodec());
		pipeline.addLast(new HttpObjectAggregator(65536));
		pipeline.addLast(new WebSocketServerCompressionHandler());
		pipeline.addLast(new WebSocketServerProtocolHandler(WEBSOCKET_PATH, null, true));
		pipeline.addLast(new SecureServerIndexPageHandler(mHTMLPage));
		pipeline.addLast(new SecureServerChannelInboundHandler(parentWebSocketServer));
	}
}