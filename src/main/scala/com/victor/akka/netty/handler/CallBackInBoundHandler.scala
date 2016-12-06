package com.victor.akka.netty.handler

import java.util.concurrent.ConcurrentHashMap

import akka.actor.ActorRef
import com.victor.akka.netty.HttpDatagram.HttpBody
import io.netty.channel.{Channel, ChannelHandler, ChannelHandlerContext, ChannelInboundHandlerAdapter}

/**
  * echo http response to actor sender
  * Created by likai on 2016/12/6.
  */
@ChannelHandler.Sharable
class CallBackInBoundHandler extends ChannelInboundHandlerAdapter {
  val map = new ConcurrentHashMap[Channel, ActorRef]()

  override def userEventTriggered(ctx: ChannelHandlerContext, resolution: scala.Any): Unit = {
    if (resolution.isInstanceOf[HttpBody]) {
      val sendActorRef = map.get(ctx.channel())
      sendActorRef ! resolution
      ctx.channel().close()
    }
  }
}
