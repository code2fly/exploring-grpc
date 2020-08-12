package com.github.mcoder;

import com.github.mcoder.service.GreetingServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

public class SampleGrpcApplication {

    public static void main(String[] args) throws Exception {

        Server server = ServerBuilder.forPort(9091)
                .addService(new GreetingServiceImpl())
                .build();

        server.start();
        System.out.println("Server started");
//        don't exit main thread , wait until server is terminated
        server.awaitTermination();

    }
}
