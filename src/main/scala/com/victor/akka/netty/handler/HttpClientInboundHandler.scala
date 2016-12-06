package com.victor.akka.netty.handler

import com.victor.akka.netty.HttpDatagram.HttpBody
import io.netty.channel.{ChannelHandlerContext, ChannelInboundHandlerAdapter}
import io.netty.handler.codec.http.{HttpContent, HttpResponse}
import io.netty.util.CharsetUtil

/**
  * user event trigger: pass resolved http response to next handler(actor)
  * Created by likai on 2016/12/6.
  */
class HttpClientInboundHandler extends ChannelInboundHandlerAdapter {
  @throws[Exception]
  override def channelRead(ctx: ChannelHandlerContext, msg: Any) {
    if (msg.isInstanceOf[HttpResponse]) {
      val response = msg.asInstanceOf[HttpResponse]
      //System.out.println("CONTENT_TYPE:" + response.headers.get(HttpHeaders.Names.CONTENT_TYPE))
    }
    if (msg.isInstanceOf[HttpContent]) {
      val content = msg.asInstanceOf[HttpContent]
      val buf = content.content
      val s = buf.toString(CharsetUtil.UTF_8)
      ctx.channel.pipeline.fireUserEventTriggered(new HttpBody(s))
      //System.out.println(s)
      buf.release
      //      ctx.channel.close
    }
  }
}
