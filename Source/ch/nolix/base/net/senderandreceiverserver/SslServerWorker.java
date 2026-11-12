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

  private final ISslCertificate mSslCertificate;

  private Channel channel;

  private SslServerWorker(
    final SslServer parentWebSocketServer,
    final int port,
    final String htmlPage,
    final ISslCertificate sslCertificate) {
    Validator.assertThat(parentWebSocketServer).thatIsNamed("parent web-socket server").isNotNull();
    Validator.assertThat(port).thatIsNamed(LowerCaseVariableNameCatalog.PORT).isPort();
    Validator.assertThat(sslCertificate).thatIsNamed(ISslCertificate.class).isNotNull();

    this.parentWebSocketServer = parentWebSocketServer;
    this.port = port;
    this.htmlPage = htmlPage;
    mSslCertificate = sslCertificate;

    start();
  }

  /**
   * @param parentWebSocketServer
   * @param port
   * @param htmlPage
   * @param sslCertificate
   * @return a new {@link SslServerWorker} for the given parentWebSocketServer,
   *         port, htmlPage and sslCertificate
   */
  public static SslServerWorker forWebSocketServerAndPortAndHtmlPageAndSslCertificate(
    final SslServer parentWebSocketServer,
    final int port,
    final String htmlPage,
    final ISslCertificate sslCertificate) {
    return new SslServerWorker(parentWebSocketServer, port, htmlPage, sslCertificate);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void run() {
    final var sslContext = SslServerSslContextCreator.createSslContext(mSslCertificate);
    final var bossGroup = new MultiThreadIoEventLoopGroup(NioIoHandler.newFactory());
    final var workerGroup = new MultiThreadIoEventLoopGroup(NioIoHandler.newFactory());

    try { // NOSONAR: bossGroup and workerGroup will be shut down gracefully.
      final var serverBootstrap = //
      new ServerBootstrap()
        .childOption(ChannelOption.TCP_NODELAY, true)
        .group(bossGroup, workerGroup)
        .channel(NioServerSocketChannel.class)
        .handler(new LoggingHandler(LogLevel.INFO))
        .childHandler(
          SslServerInitializer.forSslServerWithHtmlPageAndSslContext(parentWebSocketServer, htmlPage, sslContext));

      channel = serverBootstrap.bind(port).sync().channel();
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
