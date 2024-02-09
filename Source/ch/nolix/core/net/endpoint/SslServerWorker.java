//package declaration
package ch.nolix.core.net.endpoint;

//own imports
import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.worker.Worker;
import ch.nolix.coreapi.netapi.sslapi.ISslCertificate;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
//Netty imports
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

//class
final class SslServerWorker extends Worker {

  //constant
  private static final SslServerSslContextCreator SECURE_SERVER_SSL_CONTEXT_CREATOR = //
  new SslServerSslContextCreator();

  //attribute
  private final SslServer parentWebSocketServer;

  //attribute
  private final int port;

  //attribute
  private final String htmlPage;

  //attribute
  private final ISslCertificate mSSLCertificate;

  //attribute
  private Channel channel;

  //constructor
  public SslServerWorker(
    final SslServer parentWebSocketServer,
    final int port,
    final String htmlPage,
    final ISslCertificate paramSSLCertificate) {

    GlobalValidator.assertThat(parentWebSocketServer).thatIsNamed("parent web-socket server").isNotNull();
    GlobalValidator.assertThat(port).thatIsNamed(LowerCaseVariableCatalogue.PORT).isPort();
    GlobalValidator.assertThat(paramSSLCertificate).thatIsNamed(ISslCertificate.class).isNotNull();

    this.parentWebSocketServer = parentWebSocketServer;
    this.port = port;
    this.htmlPage = htmlPage;
    mSSLCertificate = paramSSLCertificate;

    start();
  }

  //method
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
    } catch (final InterruptedException interruptedException //NOSONAR: The Exception is rethrown wrapped in another
                                                             //Exception.
    ) {
      throw WrapperException.forError(interruptedException);
    } finally {
      bossGroup.shutdownGracefully();
      workerGroup.shutdownGracefully();
    }
  }

  //method
  void internalStop() {
    channel.close();
    channel.parent().close();
  }
}
