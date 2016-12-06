package com.victor.akka.netty

import java.net.URI

import com.victor.akka.netty.HttpDatagram.HttpFullRequest

//import akka.actor.Status.Success
import akka.actor.{ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout
import io.netty.buffer.Unpooled
import io.netty.handler.codec.http.{DefaultFullHttpRequest, HttpHeaders, HttpMethod, HttpVersion}

import scala.concurrent.duration._
import scala.util.{Failure, Success}

/**
  * test case for asynchronous -http-client
  * Created by likai on 2016/12/6.
  */
object HttpTest extends App {
  //System.setOut(new PrintStream(new FileOutputStream("C:\\Users\\likai\\Desktop\\o.txt")));
  //System.setErr(new PrintStream(new FileOutputStream("C:\\Users\\likai\\Desktop\\e.txt")));
  val system = ActorSystem("async-http-test-based-on-netty")
  system.actorOf(Props(new HttpClientActor), "http-client")

  Thread.sleep(5000)

  val httpClient = system.actorSelection("user/http-client")

  //construct full http datagram
  val uri = new URI("http://localhost:8080/package.json")
  val msg = ""
  val request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, uri.toASCIIString, Unpooled.wrappedBuffer(msg.getBytes("UTF-8")))
  //append headers
  request.headers.set(HttpHeaders.Names.HOST, "localhost:8080")
  request.headers.set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.KEEP_ALIVE)
  request.headers.set(HttpHeaders.Names.CONTENT_LENGTH, request.content.readableBytes)

  implicit val tmt = Timeout(1000000 milliseconds)

  import scala.concurrent.ExecutionContext.Implicits.global

  for (elem <- 1 to 1) {
    httpClient ? HttpFullRequest("localhost", 8080, request) andThen {
      case Failure(t) => println("Failure@" + t)
      case Success(v) => println("Success@" + v)
      case x => println("DEFAULT@" + x)
    }
  }
}
