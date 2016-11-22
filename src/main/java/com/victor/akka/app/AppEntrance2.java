package com.victor.akka.app;

import akka.actor.ActorSelection;
import akka.dispatch.ExecutionContexts;
import akka.dispatch.Futures;
import akka.pattern.AskableActorSelection;
import akka.util.Timeout;
import com.victor.akka.utils.Helper;
import scala.PartialFunction;
import scala.concurrent.ExecutionContext;
import scala.concurrent.ExecutionContextExecutor;
import scala.concurrent.Future;

import java.util.concurrent.Executors;

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
//        Future<String> future = Futures.successful("huh");

        //ExecutionContext executionContextExecutor = Helper.executionContext();
        //ExecutionContext executionContext = ExecutionContexts.fromExecutor(Executors.newFixedThreadPool(2))
        //PartialFunction<Object, String> objectStringPartialFunction = Helper.partialFunction();
        //future.onSuccess(objectStringPartialFunction, executionContext);

        //Object obj = Helper.partialFunction();
        System.out.println("terminated");
        //f.onSuccess((PartialFunction<Object, String>) objectStringPartialFunction, executionContextExecutor);
        //f.onSuccess((PartialFunction<String, Unit>) Helper.partialFunction(), Helper.executionContext());
    }
}
