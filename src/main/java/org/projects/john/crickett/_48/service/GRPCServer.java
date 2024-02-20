package org.projects.john.crickett._48.service;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GRPCServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(8070).addService(new TokenizationService()).build();
        server.start();
        server.awaitTermination();
    }
}
