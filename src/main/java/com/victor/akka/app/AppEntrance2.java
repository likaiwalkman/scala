package com.victor.akka.app;

import akka.actor.ActorSelection;
import akka.dispatch.ExecutionContexts;
import akka.pattern.AskableActorSelection;
import akka.util.Timeout;
import com.victor.akka.utils.Helper;
import scala.PartialFunction;
import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AppEntrance2 {
    public static void main(String[] args) throws InterruptedException {
        ActorContext.main(args);
        ActorSelection actorSelection = ActorContext.system().actorSelection("/user/master/commander");
        // 1、! synchronous invoke
        //actorSelection.tell("Hello", actorSelection.anchor());

        // 2.1、? asynchronous invoke : wait
        /*Future<Object> future = (new AskableActorSelection(actorSelection)).ask("WorkReady", new Timeout(5000));
        TimeUnit.SECONDS.sleep(5);
        Option<Try<Object>> value = future.value();
        Try<Object> objectTry = value.get();
        Object o = objectTry.get();
        System.out.println(o);*/

        // 2.2、? asynchronous invoke : callback
        final Future<Object> f = (new AskableActorSelection(actorSelection)).ask("WorkReady", new Timeout(5000));

        ExecutorService                 executorService = Executors.newFixedThreadPool(2);
        ExecutionContext                ec              = ExecutionContexts.fromExecutor(executorService);
        PartialFunction<Object, String> pf              = Helper.partialFunction();
        f.onSuccess(pf, ec);
        System.out.println("terminated in AppEntrance2");
        executorService.awaitTermination(2, TimeUnit.SECONDS);
    }
}
