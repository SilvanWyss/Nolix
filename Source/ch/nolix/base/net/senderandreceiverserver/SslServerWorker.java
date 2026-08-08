/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.senderandreceiverserver;

import ch.nolix.base.errorcontrol.generalexception.WrapperException;
import ch.nolix.base.programcontrol.worker.AbstractWorker;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.baseapi.net.ssl.ISslCertificate;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.MultiThreadIoEventLoopGroup;
import io.netty.channel.nio.NioIoHandler;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

final class SslServerWorker extends AbstractWorker {
  private final SslServer parentWebSocketServer;

  private final int port;

  private final String htmlPage;

  private final ISslCertificate mSSLCertificate;

  private Channel channel;

  private SslServerWorker(
    final SslServer parentWebSocketServer,
    final int port,
    final String htmlPage,
    final ISslCertificate paramSSLCertificate) {
    Validator.assertThat(parentWebSocketServer).thatIsNamed("parent web-socket server").isNotNull();
    Validator.assertThat(port).thatIsNamed(LowerCaseVariableNameCatalog.PORT).isPort();
    Validator.assertThat(paramSSLCertificate).thatIsNamed(ISslCertificate.class).isNotNull();

    this.parentWebSocketServer = parentWebSocketServer;
    this.port = port;
    this.htmlPage = htmlPage;
    mSSLCertificate = paramSSLCertificate;

    start();
  }

  public static SslServerWorker forWebSocketServerAndPortAndHtmlPageAndSslCertificate(
    final SslServer parentWebSocketServer,
    final int port,
    final String htmlPage,
    final ISslCertificate paramSSLCertificate) {
    return new SslServerWorker(parentWebSocketServer, port, htmlPage, paramSSLCertificate);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void run() {
    final var sslContext = SslServerSslContextCreator.createSSLContext(mSSLCertificate);
    final var bossGroup = new MultiThreadIoEventLoopGroup(NioIoHandler.newFactory());
    final var workerGroup = new MultiThreadIoEventLoopGroup(NioIoHandler.newFactory());

    try { // NOSONAR: bossGroup and workerGroup will be shut down gracefully.
      final var serverBootstrab = //
      new ServerBootstrap()
        .childOption(ChannelOption.TCP_NODELAY, true)
        .group(bossGroup, workerGroup)
        .channel(NioServerSocketChannel.class)
        .handler(new LoggingHandler(LogLevel.INFO))
        .childHandler(
          SslServerInitializer.forSslServerWithHtmlPageAndSslContext(parentWebSocketServer, htmlPage, sslContext));

      channel = serverBootstrab.bind(port).sync().channel();
      channel.closeFuture().sync();
    } catch (final InterruptedException interruptedException // NOSONAR: The Exception is wrapped.
    ) {
      throw WrapperException.forError(interruptedException);
    } finally {
      bossGroup.shutdownGracefully();
      workerGroup.shutdownGracefully();
    }
  }

  void internalStop() {
    channel.close();
    channel.parent().close();
  }
}
