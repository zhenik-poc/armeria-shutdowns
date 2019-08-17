package com.zhenik.armeria.shutdowns;

import com.linecorp.armeria.common.HttpResponse;
import com.linecorp.armeria.server.Server;
import com.linecorp.armeria.server.ServerBuilder;
import com.zhenik.armeria.shutdowns.domain.Integration;

// server not shutdown, integration shutdown
public class Example1 {
    public static void main(String[] args) {
        Server armeriaServer = new ServerBuilder()
                .http(8080)
                .service("/", (ctx, req) -> HttpResponse.of("OK")).build();
        armeriaServer.start().join(); // main thread
        new Integration(true).run(); // main thread
    }
}
