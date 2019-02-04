package ru.ifmo.wst.lab1;

import lombok.extern.slf4j.Slf4j;
import ru.ifmo.wst.lab1.ws.TestService;
import ru.ifmo.wst.util.Configuration;

import javax.xml.ws.Endpoint;

@Slf4j
public class App {
    public static void main(String[] args) {
        Configuration conf = new Configuration("config.properties");
        String scheme = conf.get("scheme", "http:");
        String host = conf.get("host", "localhost");
        String port = conf.get("port", "8080");
        String serviceName = conf.get("test.service.name", "TestService");
        String baseUrl = scheme + "//" + host + ":" + port;
        String testServiceUrl = baseUrl + "/" + serviceName;
        log.info("Start application");
        Endpoint publish = Endpoint.publish(testServiceUrl, new TestService());
        log.info("Application was successfully started");
    }
}
