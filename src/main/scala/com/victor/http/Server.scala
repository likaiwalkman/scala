/*
package com.victor.http

import java.util

import akka.actor.{Actor, ActorRefFactory}
import com.alibaba.fastjson.JSON
import spray.http.HttpHeaders.RawHeader
import spray.http.StatusCodes
import spray.routing.{RejectionHandler, _}

class Server extends Actor with HttpService {
  override def receive: Receive = runRoute(route)

  override implicit def actorRefFactory: ActorRefFactory = context

  implicit def executionContext = actorRefFactory.dispatcher

  def route: Route = {
    requestInstance { request =>
      requestUri { uri =>
        val url = uri.path.toString();
        if ("report_sms_rl" != url && "sms_send" != url) {
          optionalCookie("_tk") {
            case Some(_tk) =>
              val appId: Long = 90019L;
              //mocked service to get result
              val result = new util.HashMap[String, Object]
              result.put("success", false);
              result.put("hasError", true);
              optionalHeaderValueByName("origin") { originOption =>
                val origin = originOption match {
                  case Some(value) => value
                  case None => ""
                }
                respondWithHeaders(
                  RawHeader("Access-Control-Allow-Origin", origin),
                  RawHeader("Access-Control-Allow-Method", "GET,POST,OPTIONS"),
                  RawHeader("Access-Control-Allow-Headers", "x-requested-with,Content-Type"),
                  RawHeader("Access-Control-Allow-Credentials", "true")) {
                  complete(JSON.toJSONString(result, true));
                }
              }
            case None =>
              handleRejections(corsAwareRejectionHandler) {
                noTKRoute ~ innerRoute
              }
          }
        } else {
          smsReportRoute
        }
      }
    }
  }

  def noTKRoute: Route = {
    optionalHeaderValueByName("origin") { originOption =>
      val origin = originOption match {
        case Some(value) => value
        case None => ""
      }
      respondWithHeaders(
        RawHeader("Access-Control-Allow-Origin", origin),
        RawHeader("Access-Control-Allow-Method", "GET,POST,OPTIONS"),
        RawHeader("Access-Control-Allow-Headers", "x-requested-with,Content-Type"),
        RawHeader("Access-Control-Allow-Credentials", "true")) {
        smsReportRoute
        options {
          complete("We do support CORS preflight.")
        }
      }
    }
  }

  def innerRoute: Route = {
    optionalHeaderValueByName("origin") { originOption =>
      val origin = originOption match {
        case Some(value) => value
        case None => ""
      }
      respondWithHeaders(
        RawHeader("Access-Control-Allow-Origin", origin),
        RawHeader("Access-Control-Allow-Method", "GET,POST,OPTIONS"),
        RawHeader("Access-Control-Allow-Headers", "x-requested-with,Content-Type"),
        RawHeader("Access-Control-Allow-Credentials", "true")) {
        //fileRoute
        //TODO
        options {
          complete("We do support CORS preflight.")
        }
      }
    }
  }

  val corsAwareRejectionHandler = RejectionHandler {
    case Nil =>
      complete(StatusCodes.NotFound, "404, but we do support CORS.")
  }
  val smsReportRoute: Route = path("sms_report") {
    get {
      parameterMap { params =>
        complete {
          val pageSize = 10
          val index = java.lang.Integer.valueOf(params("index"))
          val mobile = params("mobile")
          val user = params("user")
          println(pageSize, index, mobile, user)
          val resultMap = new java.util.HashMap[String, AnyRef]()
          //mocked service to get result
          resultMap.put("smsMessages", new util.LinkedList[Object]())
          resultMap.put("count", 0)
          JSON.toJSONString(resultMap, true)
        }
      }
    }
  }
}
*/
