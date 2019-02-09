package ru.ifmo.wst.lab1;

import lombok.SneakyThrows;
import ru.ifmo.wst.lab1.ws.client.standalone.ExterminatusEntity;
import ru.ifmo.wst.lab1.ws.client.standalone.ExterminatusService;
import ru.ifmo.wst.lab1.ws.client.standalone.ExterminatusServiceService;

import javax.xml.ws.BindingProvider;
import java.util.List;

public class ConsoleClient {
    @SneakyThrows
    public static void main(String[] args) {
        ExterminatusServiceService exterminatusService = new ExterminatusServiceService();
        ExterminatusService service = exterminatusService.getExterminatusServicePort();
        BindingProvider bindingProvider = (BindingProvider) service;
        bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, "http://localhost:8080/EXTERMINATE");
        List<ExterminatusEntity> filter = service.filter(null, "инк", null, null, null, null);
        System.out.println(filter);
    }
}
