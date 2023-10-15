//package declaration
package ch.nolix.core.net.endpoint;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.netapi.netproperty.ConnectionType;
import ch.nolix.coreapi.netapi.netproperty.PeerType;
import ch.nolix.coreapi.programcontrolapi.processproperty.SecurityLevel;
import ch.nolix.coreapi.programcontrolapi.processproperty.TargetInfoState;
//Netty imports
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

//class
final class SecureServerEndPoint extends NetEndPoint {

  private final ChannelHandlerContext channelHandlerContext;

  // constructor
  public SecureServerEndPoint(final ChannelHandlerContext channelHandlerContext) {

    super(TargetInfoState.WAITS_TO_TARGET_INFO);

    GlobalValidator.assertThat(channelHandlerContext).thatIsNamed(ChannelHandlerContext.class).isNotNull();

    this.channelHandlerContext = channelHandlerContext;
  }

  // method
  @Override
  public ConnectionType getConnectionType() {
    return ConnectionType.WEB_SOCKET;
  }

  // method
  @Override
  public PeerType getPeerType() {
    return PeerType.BACKEND;
  }

  // method
  /**
   * {@inheritDoc}
   */
  @Override
  public SecurityLevel getConnectionSecurityLevel() {
    return SecurityLevel.SECURE;
  }

  // method
  @Override
  public void noteClose() {
    channelHandlerContext.close();
  }

  // method
  @Override
  protected void sendRawMessage(final String rawMessage) {
    channelHandlerContext.channel().writeAndFlush(new TextWebSocketFrame(rawMessage + "\r\n"));
  }
}
