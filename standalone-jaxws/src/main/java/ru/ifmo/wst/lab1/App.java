package ru.ifmo.wst.lab1;

import ru.ifmo.wst.lab1.ws.TestService;

import javax.xml.ws.Endpoint;

public class App {
    public static void main(String[] args) {
        Endpoint publish = Endpoint.publish("http://localhost:8080/TestService", new TestService());
        System.out.println("Started!");
    }
}
