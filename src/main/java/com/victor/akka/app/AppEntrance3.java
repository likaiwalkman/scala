package com.victor.akka.app;

import akka.dispatch.Futures;
import com.victor.akka.utils.Helper;
import scala.PartialFunction;
import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;

/*
* demonstrate how to leverage scala to implement partialFunction in java
*/
public class AppEntrance3 {
    public static void main(String[] args) throws InterruptedException {
        Future<Object> future = Futures.successful((Object) "Over");
        ExecutionContext                ec = Helper.executionContext();
        PartialFunction<Object, String> pf = Helper.partialFunction();
        future.onSuccess(pf, ec);
        Thread.sleep(3000);
    }
}
