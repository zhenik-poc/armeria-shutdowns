package com.zhenik.armeria.shutdowns;

import com.linecorp.armeria.common.HttpResponse;
import com.linecorp.armeria.server.Server;
import com.linecorp.armeria.server.ServerBuilder;
import com.zhenik.armeria.shutdowns.domain.Integration;

public class Example2 {
    public static void main(String[] args) {
        Server armeriaServer = new ServerBuilder()
                .http(8080)
                .service("/", (ctx, req) -> HttpResponse.of("OK")).build();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> armeriaServer.stop().join())); // hook
        armeriaServer.start().join(); // main thread
        new Integration().run(); // main thread


        // continue
    }
}
