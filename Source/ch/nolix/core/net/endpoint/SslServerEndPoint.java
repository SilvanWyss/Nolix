package ch.nolix.core.net.endpoint;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.net.netproperty.ConnectionType;
import ch.nolix.coreapi.net.netproperty.PeerType;
import ch.nolix.coreapi.net.securityproperty.SecurityMode;
import ch.nolix.coreapi.programcontrol.processproperty.TargetInfoState;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

final class SslServerEndPoint extends AbstractNetEndPoint {
  private final ChannelHandlerContext channelHandlerContext;

  public SslServerEndPoint(final ChannelHandlerContext channelHandlerContext) {
    super(TargetInfoState.WAITS_TO_TARGET_INFO);

    Validator.assertThat(channelHandlerContext).thatIsNamed(ChannelHandlerContext.class).isNotNull();

    this.channelHandlerContext = channelHandlerContext;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ConnectionType getConnectionType() {
    return ConnectionType.WEB_SOCKET;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public PeerType getPeerType() {
    return PeerType.BACKEND;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public SecurityMode getSecurityMode() {
    return SecurityMode.SSL;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void noteClose() {
    channelHandlerContext.close();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void sendRawMessage(final String rawMessage) {
    channelHandlerContext.channel().writeAndFlush(new TextWebSocketFrame(rawMessage + "\r\n"));
  }
}
