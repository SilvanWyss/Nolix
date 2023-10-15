//package declaration
package ch.nolix.core.net.endpoint;

//Netty imports
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.util.CharsetUtil;
import static io.netty.handler.codec.http.HttpHeaderNames.*;
import static io.netty.handler.codec.http.HttpMethod.*;
import static io.netty.handler.codec.http.HttpResponseStatus.*;

//class
final class SecureServerIndexPageHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

  private final String htmlPage;

  public SecureServerIndexPageHandler(String htmlPage) {
    this.htmlPage = htmlPage;
  }

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest req) throws Exception {
    // Handle a bad request.
    if (!req.decoderResult().isSuccess()) {
      sendHttpResponse(ctx, req,
          new DefaultFullHttpResponse(req.protocolVersion(), BAD_REQUEST, ctx.alloc().buffer(0)));
      return;
    }

    // Allow only GET methods.
    if (!GET.equals(req.method())) {
      sendHttpResponse(ctx, req, new DefaultFullHttpResponse(req.protocolVersion(), FORBIDDEN, ctx.alloc().buffer(0)));
      return;
    }

    // Send the index page
    ByteBuf content = Unpooled.copiedBuffer(htmlPage, CharsetUtil.US_ASCII);
    FullHttpResponse res = new DefaultFullHttpResponse(req.protocolVersion(), OK, content);
    res.headers().set(CONTENT_TYPE, "text/html; charset=UTF-8");
    HttpUtil.setContentLength(res, content.readableBytes());
    sendHttpResponse(ctx, req, res);
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    cause.printStackTrace();
    ctx.close();
  }

  private static void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, FullHttpResponse res) {
    // Generate an error page if response getStatus code is not OK (200).
    HttpResponseStatus responseStatus = res.status();
    if (responseStatus.code() != 200) {
      ByteBufUtil.writeUtf8(res.content(), responseStatus.toString());
      HttpUtil.setContentLength(res, res.content().readableBytes());
    }

    // Send the response and close the connection if necessary.
    boolean keepAlive = HttpUtil.isKeepAlive(req) && responseStatus.code() == 200;
    HttpUtil.setKeepAlive(res, keepAlive);
    ChannelFuture future = ctx.writeAndFlush(res);
    if (!keepAlive) {
      future.addListener(ChannelFutureListener.CLOSE);
    }
  }
}
