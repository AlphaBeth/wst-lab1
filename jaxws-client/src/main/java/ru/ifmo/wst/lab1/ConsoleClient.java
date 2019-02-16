package ru.ifmo.wst.lab1;

import lombok.SneakyThrows;
import ru.ifmo.wst.lab1.client.ExterminatusServiceConsoleClient;
import ru.ifmo.wst.lab1.ws.client.ExterminatusService;
import ru.ifmo.wst.lab1.ws.client.ExterminatusServiceService;

import javax.xml.ws.BindingProvider;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ConsoleClient {
    @SneakyThrows
    public static void main(String[] args) {
        ExterminatusServiceService exterminatusService = new ExterminatusServiceService();
        ExterminatusService service = exterminatusService.getExterminatusServicePort();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BindingProvider bindingProvider = (BindingProvider) service;
        String endpointUrl;
        endpointUrl = "http://localhost:8080/EXTERMINATE";
        System.out.print("Enter endpoint url (or empty string for default " + endpointUrl + ")\n> ");
        String line = bufferedReader.readLine();
        if (!line.trim().isEmpty()) {
            endpointUrl = line.trim();
        }
        bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointUrl);

        ExterminatusServiceConsoleClient exterminatusServiceConsoleClient = new ExterminatusServiceConsoleClient(service);
        exterminatusServiceConsoleClient.info();
        exterminatusServiceConsoleClient.start();

    }

}
