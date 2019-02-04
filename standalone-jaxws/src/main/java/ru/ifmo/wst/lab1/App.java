package ru.ifmo.wst.lab1;

import lombok.extern.slf4j.Slf4j;
import ru.ifmo.wst.lab1.ws.TestService;

import javax.xml.ws.Endpoint;

@Slf4j
public class App {
    public static void main(String[] args) {
        log.info("Start application");
        Endpoint publish = Endpoint.publish("http://localhost:8080/TestService", new TestService());
        log.info("Application was successfully started");
    }
}
