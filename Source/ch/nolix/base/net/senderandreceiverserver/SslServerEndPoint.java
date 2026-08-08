/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.senderandreceiverserver;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.net.netproperty.ConnectionType;
import ch.nolix.baseapi.net.netproperty.PeerType;
import ch.nolix.baseapi.net.netproperty.SecurityMode;
import ch.nolix.baseapi.net.senderandreceiverserver.TargetInfoState;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * @author Silvan Wyss
 */
public final class SslServerEndPoint extends AbstractNetEndPoint {
  private final ChannelHandlerContext channelHandlerContext;

  /**
   * Creates a new {@link SslServerEndPoint} with the given channelHandlerContext.
   *
   * @param channelHandlerContext
   * @throws RuntimeException if the given channelHandlerContext is null
   */
  private SslServerEndPoint(final ChannelHandlerContext channelHandlerContext) {
    super(TargetInfoState.WAITING_TO_TARGET_INFO);

    Validator.assertThat(channelHandlerContext).thatIsNamed(ChannelHandlerContext.class).isNotNull();

    this.channelHandlerContext = channelHandlerContext;
  }

  /**
   * @param channelHandlerContext
   * @return a new {@link SslServerEndPoint} with the given channelHandlerContext
   * @throws RuntimeException if the given channelHandlerContext is null
   */
  public static SslServerEndPoint withChannelHandlerContext(final ChannelHandlerContext channelHandlerContext) {
    return new SslServerEndPoint(channelHandlerContext);
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
