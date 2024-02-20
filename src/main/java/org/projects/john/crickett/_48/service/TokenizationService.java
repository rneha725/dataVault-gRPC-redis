package org.projects.john.crickett._48.service;

import io.grpc.stub.StreamObserver;
import io.netty.handler.codec.http.HttpResponseStatus;
import org.projects.john.crickett._48.data.vault.*;
import redis.clients.jedis.JedisPooled;

import java.util.HashMap;
import java.util.Map;

public class TokenizationService extends TokenizationServiceGrpc.TokenizationServiceImplBase {
    @Override
    public void tokenize(TokenCreationRequest request, StreamObserver<TokenCreationResponse> responseObserver) {
        JedisPooled jedis = Jedis.getInstance();
        String userId = request.getUserId();
        Map<String, String> kvPairs = request.getValuesMap();
        Map<String, String> responseDataMap = new HashMap<>();

        kvPairs.forEach((key, value) -> {
            String token = Utils.generateToken(value);
            jedis.set(token, value); //update token also
            jedis.set(token + userId, value);
            responseDataMap.put(key, token);
        });

        TokenCreationResponse response = TokenCreationResponse.newBuilder().putAllTokens(responseDataMap).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void detokenize(TokenCreationRequest request, StreamObserver<TokenCreationResponse> responseObserver) {
        JedisPooled jedis = Jedis.getInstance();
        String userId = request.getUserId();
        Map<String, String> kvPairs = request.getValuesMap();
        Map<String, String> responseDataMap = new HashMap<>();

        kvPairs.forEach((key, token) -> {
            String value = jedis.get(token + userId);
            responseDataMap.put(key, value);
        });

        TokenCreationResponse response = TokenCreationResponse.newBuilder().putAllTokens(responseDataMap).build();
        responseObserver.onNext(response);
        responseObserver.onError(new Exception("Not expected"));
        responseObserver.onCompleted();
    }

    @Override
    public void addPermission(PermissionRequest request, StreamObserver<PermissionResponse> responseObserver) {
        JedisPooled jedis = Jedis.getInstance();
        String userId = request.getUserId();
        String token = request.getToken();
        String value = jedis.get(token);

        jedis.set(token + userId, value);

        PermissionResponse response = PermissionResponse.newBuilder().setStatus(String.valueOf(HttpResponseStatus.OK)).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void removePermission(PermissionRequest request, StreamObserver<PermissionResponse> responseObserver) {
        JedisPooled jedis = Jedis.getInstance();
        String userId = request.getUserId();
        String token = request.getToken();

        jedis.del(token + userId);

        PermissionResponse response = PermissionResponse.newBuilder().setStatus(String.valueOf(HttpResponseStatus.OK)).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
