package com.victor.akka.netty

import io.netty.handler.codec.http.DefaultFullHttpRequest

/**
  * Created by likai on 2016/12/6.
  */
object HttpDatagram {

  case class HttpBody(body: String)

  case class HttpFullRequest(hostAddress: String, port: Int, fullDatagram: DefaultFullHttpRequest)

}
