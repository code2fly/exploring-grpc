package com.github.mcoder.service;

import com.github.mcoder.GreetingServiceGrpc;
import com.github.mcoder.GreetingServiceOuterClass;
import io.grpc.stub.StreamObserver;

public class GreetingServiceImpl extends GreetingServiceGrpc.GreetingServiceImplBase {

    @Override
    public void greeting(GreetingServiceOuterClass.HelloRequest request, StreamObserver<GreetingServiceOuterClass.HelloResponse> responseObserver) {
        System.out.println(request);

        GreetingServiceOuterClass.HelloResponse response = GreetingServiceOuterClass.HelloResponse.newBuilder()
                .setGreeting("Hello there , " + request.getName())
                .build();

//        to send a single response back
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
