package ch.nolix.core.net.endpoint;

import ch.nolix.core.errorcontrol.generalexception.WrapperException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.programcontrol.worker.AbstractWorker;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.coreapi.net.ssl.ISslCertificate;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

final class SslServerWorker extends AbstractWorker {
  private static final SslServerSslContextCreator SECURE_SERVER_SSL_CONTEXT_CREATOR = //
  new SslServerSslContextCreator();

  private final SslServer parentWebSocketServer;

  private final int port;

  private final String htmlPage;

  private final ISslCertificate mSSLCertificate;

  private Channel channel;

  public SslServerWorker(
    final SslServer parentWebSocketServer,
    final int port,
    final String htmlPage,
    final ISslCertificate paramSSLCertificate) {
    Validator.assertThat(parentWebSocketServer).thatIsNamed("parent web-socket server").isNotNull();
    Validator.assertThat(port).thatIsNamed(LowerCaseVariableCatalog.PORT).isPort();
    Validator.assertThat(paramSSLCertificate).thatIsNamed(ISslCertificate.class).isNotNull();

    this.parentWebSocketServer = parentWebSocketServer;
    this.port = port;
    this.htmlPage = htmlPage;
    mSSLCertificate = paramSSLCertificate;

    start();
  }

  @Override
  protected void run() {
    final var sslContext = SECURE_SERVER_SSL_CONTEXT_CREATOR.createSSLContext(mSSLCertificate);
    final var bossGroup = new NioEventLoopGroup(1);
    final var workerGroup = new NioEventLoopGroup();

    try { //NOSONAR: bossGroup and workerGroup will be shut down gracefully.

      final var serverBootstrab = new ServerBootstrap()
        .childOption(ChannelOption.TCP_NODELAY, true)
        .group(bossGroup, workerGroup)
        .channel(NioServerSocketChannel.class)
        .handler(new LoggingHandler(LogLevel.INFO))
        .childHandler(new SslServerInitializer(parentWebSocketServer, htmlPage, sslContext));

      channel = serverBootstrab.bind(port).sync().channel();
      channel.closeFuture().sync();
    } catch (final InterruptedException interruptedException //NOSONAR: The Exception is wrapped.
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
