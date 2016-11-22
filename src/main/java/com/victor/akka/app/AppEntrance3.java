package com.victor.akka.app;

import akka.dispatch.ExecutionContexts;
import akka.dispatch.Futures;
import com.victor.akka.utils.Helper;
import scala.PartialFunction;
import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppEntrance3 {
    public static void main(String[] args) throws InterruptedException {
        Future<Object> future = Futures.successful((Object) "huh");
        ExecutorService executor = Executors.newFixedThreadPool(2);
        ExecutionContext ec = ExecutionContexts.fromExecutor(executor);
        /*PartialFunction<Object, String> pf = Helper.partialFunction();
        future.onSuccess(pf, ec);
        executor.shutdown();*/
    }
}
