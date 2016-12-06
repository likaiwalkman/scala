package com.victor.akka.netty

import java.net.InetSocketAddress

import akka.actor.Actor
import com.victor.akka.netty.HttpDatagram.HttpFullRequest
import com.victor.akka.netty.handler.{CallBackInBoundHandler, HttpClientInboundHandler}
import io.netty.bootstrap.Bootstrap
import io.netty.channel._
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioSocketChannel
import io.netty.handler.codec.http.{HttpRequestEncoder, HttpResponseDecoder}

/**
  * http client implemented with netty : leveraging non-blocking io pattern and thread pool to improve system throughput
  * Created by likai on 2016/12/6.
  */
class HttpClientActor extends Actor {

  //set up netty pooled-thread based on nio pattern to make connections processing ready
  val workerGroup = new NioEventLoopGroup(5)
  val b = new Bootstrap
  b.group(workerGroup)
  b.channel(classOf[NioSocketChannel])
  //b.option(ChannelOption.WRITE_BUFFER_HIGH_WATER_MARK.asInstanceOf[ChannelOption[Any]], 32 * 1024)
  //b.option(ChannelOption.WRITE_BUFFER_LOW_WATER_MARK.asInstanceOf[ChannelOption[Any]], 8 * 1024)
  b.option(ChannelOption.SO_KEEPALIVE.asInstanceOf[ChannelOption[Any]], true)
  val callBackInBoundHandler = new CallBackInBoundHandler
  b.handler(new ChannelInitializer[SocketChannel]() {
    @throws[Exception]
    def initChannel(ch: SocketChannel) {
      // http response decoder
      ch.pipeline.addLast(new HttpResponseDecoder)
      // http request encoder
      ch.pipeline.addLast(new HttpRequestEncoder)
      //trigger next handler event to happen
      ch.pipeline.addLast(new HttpClientInboundHandler)
      //registered call back when http response is resolved
      ch.pipeline.addLast(callBackInBoundHandler)
    }
  })

  override def receive = {
    case HttpFullRequest(hostAddress, port, fullDatagram) => {
      val _sender = sender()
      //      println(_sender)
      val cf = b.connect(new InetSocketAddress(hostAddress, port))
      cf.addListener(new ChannelFutureListener {
        override def operationComplete(f: ChannelFuture): Unit = {
          //connect successfully
          if (f.isSuccess) {
            System.out.println("Connection established")
            callBackInBoundHandler.map.put(cf.channel(), _sender)
            cf.channel().write(fullDatagram)
            cf.channel().flush()
          }
          //connect failure
          else {
            //System.err.println("Connection attempt failed")
            sender() ! f.cause()
            //f.cause.printStackTrace()
          }
        }
      })
    }
    case x => {
      println(s"ignore msg $x")
    }
  }
}
