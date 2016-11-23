package com.victor;

import akka.dispatch.ExecutionContexts;
import scala.PartialFunction;
import scala.concurrent.ExecutionContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestPartial {

    public static void main(String[] args) throws Exception {
        ExecutorService es = Executors.newFixedThreadPool(10);
        ExecutionContext ec = ExecutionContexts.fromExecutorService(es);
        PartialFunction<Object, String> pf = Partial.pf();
        /*Future<Object> future = Futures.successful("huh");
        future.onSuccess(pf, ec);
        es.shutdown();*/
    }
}
