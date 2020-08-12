package com.github.mcoder.client;

import com.github.mcoder.GreetingServiceGrpc;
import com.github.mcoder.GreetingServiceOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class HelloServiceClient {

    public static void main(String[] args) {
        final ManagedChannel channel =
                ManagedChannelBuilder
                        .forAddress("localhost", 9091)
                        .usePlaintext()
                        .build();

//        it is upto client weather to block the call , for blocking we have blocking stub and async or future stub for non blocking call
        GreetingServiceGrpc.GreetingServiceBlockingStub greetingServiceBlockingStub = GreetingServiceGrpc.newBlockingStub(channel);

        GreetingServiceOuterClass.HelloRequest request = GreetingServiceOuterClass.HelloRequest.newBuilder()
                .setName("Lalu")
                .build();

        GreetingServiceOuterClass.HelloResponse response = greetingServiceBlockingStub.greeting(request);

        System.out.println("****** response from server " + response);

//        channel should be shutdown before stopping the process
        channel.shutdownNow();
    }
}
