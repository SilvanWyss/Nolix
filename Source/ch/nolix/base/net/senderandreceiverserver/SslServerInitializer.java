/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.senderandreceiverserver;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.ssl.SslContext;

final class SslServerInitializer extends ChannelInitializer<SocketChannel> {
  private static final String WEBSOCKET_PATH = "/websocket"; // NOSONAR: This constant is not a URI.

  private final SslServer parentSslServer;

  private final String htmlPage;

  private final SslContext sslContext;

  private SslServerInitializer(
    final SslServer parentSslServer,
    final String htmlPage,
    final SslContext sslContext) {
    this.parentSslServer = parentSslServer;
    this.htmlPage = htmlPage;
    this.sslContext = sslContext;
  }

  public static SslServerInitializer forSslServerWithHtmlPageAndSslContext(
    final SslServer sslServer,
    final String htmlPage,
    final SslContext sslContext) {
    return new SslServerInitializer(sslServer, htmlPage, sslContext);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void initChannel(SocketChannel ch) throws Exception {
    final var pipeline = ch.pipeline();

    if (sslContext != null) {
      pipeline.addLast(sslContext.newHandler(ch.alloc()));
    }

    pipeline.addLast(new HttpServerCodec());
    pipeline.addLast(new HttpObjectAggregator(65536));
    pipeline.addLast(new WebSocketServerCompressionHandler(0));
    pipeline.addLast(new WebSocketServerProtocolHandler(WEBSOCKET_PATH, null, true));
    pipeline.addLast(SslServerIndexPageHandler.withHtmlPage(htmlPage));
    pipeline.addLast(SslServerChannelInboundHandler.forSslServer(parentSslServer));
  }
}
