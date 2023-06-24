//package declaration
package ch.nolix.core.net.endpoint;

//Netty imports
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;

//own imports
import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programcontrol.worker.Worker;
import ch.nolix.coreapi.netapi.tlsapi.ISSLCertificate;

//class
final class SecureServerWorker extends Worker {
	
	//constant
	private static final SecureServerSSLContextCreator WEB_SOCKET_SERVER_SSL_CONTEXT_CREATOR =
	SecureServerSSLContextCreator.INSTANCE;
	
	//attribute
	private final SecureServer parentWebSocketServer;
	
	//attribute
	private final int port;
	
	//attribute
	private final String htmlPage;
	
	//attribute
	private final ISSLCertificate mSSLCertificate;
	
	//constructor
	public SecureServerWorker(
		final SecureServer parentWebSocketServer,
		final int port,
		final String htmlPage,
		final ISSLCertificate paramSSLCertificate
	) {
		
		GlobalValidator.assertThat(parentWebSocketServer).thatIsNamed("parent web-socket server").isNotNull();
		GlobalValidator.assertThat(port).thatIsNamed(LowerCaseCatalogue.PORT).isPort();
		GlobalValidator.assertThat(paramSSLCertificate).thatIsNamed(ISSLCertificate.class).isNotNull();
		
		this.parentWebSocketServer = parentWebSocketServer;
		this.port = port;
		this.htmlPage = htmlPage;
		mSSLCertificate = paramSSLCertificate;
		
		start();
	}
	
	//method
	@Override
	protected void run() {
		
		SslContext sslCtx = WEB_SOCKET_SERVER_SSL_CONTEXT_CREATOR.createSSLContext(mSSLCertificate);
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.childOption(ChannelOption.TCP_NODELAY, true);
			b.group(bossGroup, workerGroup)
			.channel(NioServerSocketChannel.class)
			.handler(new LoggingHandler(LogLevel.INFO))
			.childHandler(new SecureServerInitializer(parentWebSocketServer, htmlPage, sslCtx));
			Channel ch = b.bind(port).sync().channel();
			ch.closeFuture().sync();
		} catch (InterruptedException interruptedException) {
			throw WrapperException.forError(interruptedException);
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
}
