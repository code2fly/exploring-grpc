package com.github.mcoder;

import com.github.mcoder.service.GreetingServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

@Slf4j
@SpringBootApplication
public class SampleGrpcApplication {


    @Autowired
    private Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(SampleGrpcApplication.class, args);
    }


    @PostConstruct
    public void startGrpcServer() {
        String port = environment.getProperty("grpc.server.port", "9091");
        Server server = ServerBuilder.forPort(Integer.valueOf(port))
                .addService(new GreetingServiceImpl())
                .build();

        Runtime.getRuntime().addShutdownHook(new Thread(
                () -> {
                    log.info("shutting down server");
                    server.shutdown();
                }
        ));

        try {
            server.start();
            log.info("server started on port : {}", port);
            server.awaitTermination();
        }
        catch (Exception ex) {
//        don't exit main thread , wait until server is terminated
            log.error("error while starting server : {}", ex);
        }


    }
}
