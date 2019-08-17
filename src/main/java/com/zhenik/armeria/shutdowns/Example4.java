package com.zhenik.armeria.shutdowns;

import com.linecorp.armeria.common.HttpResponse;
import com.linecorp.armeria.server.Server;
import com.linecorp.armeria.server.ServerBuilder;
import com.zhenik.armeria.shutdowns.domain.Integration;

import java.util.concurrent.CompletableFuture;

public class Example4 {
    public static void main(String[] args) {
        Server armeriaServer = new ServerBuilder()
                .http(8080)
                .service("/", (ctx, req) -> HttpResponse.of("OK")).build();

//        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
//            System.out.println("Server is going to shutdown");
//            armeriaServer.stop().join();
//        }));

        armeriaServer.start().join(); // main thread

        CompletableFuture
                .supplyAsync(() -> {
                    new Integration().run();
                    return null;
                })
                .exceptionally(throwable -> {
                    System.out.println("print exception from child thread in main");
                    throwable.printStackTrace();
                    return throwable;
                })
                .thenApply(ignored -> {
                    System.out.println("Server is going to shutdown 'thenApply' ");
                    return armeriaServer.stop(); // will trigger also shutdown line 16-19
                })
                .thenAccept(CompletableFuture::join); // not necessary

        // shutdown
    }
}
