package ch.nolix.core.net.endpoint;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.ssl.SslContext;

final class SslServerInitializer extends ChannelInitializer<SocketChannel> {
  private static final String WEBSOCKET_PATH = "/websocket"; //NOSONAR: This constant is not a URI.

  private final SslServer parentWebSocketServer;

  private final String htmlPage;

  private final SslContext sslCtx;

  public SslServerInitializer(SslServer parentWebSocketServer, String htmlPage, SslContext sslCtx) {
    this.parentWebSocketServer = parentWebSocketServer;
    this.htmlPage = htmlPage;
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
    pipeline.addLast(new WebSocketServerCompressionHandler(0));
    pipeline.addLast(new WebSocketServerProtocolHandler(WEBSOCKET_PATH, null, true));
    pipeline.addLast(new SslServerIndexPageHandler(htmlPage));
    pipeline.addLast(new SslServerChannelInboundHandler(parentWebSocketServer));
  }
}
