package com.servicepoller;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

public class App {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx(new VertxOptions().setWorkerPoolSize(40).setBlockedThreadCheckInterval(300L * 1000 * 1000 * 1000));
        vertx.deployVerticle(APIServer.class.getName(), new DeploymentOptions().setWorker(true).setWorkerPoolSize(10));
        vertx.deployVerticle(new PollingAgent(), new DeploymentOptions().setWorker(true).setWorkerPoolSize(1).setWorkerPoolName("periodic"));
    }
}
