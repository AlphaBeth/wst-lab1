package ru.ifmo.wst.lab1;

import lombok.SneakyThrows;
import ru.ifmo.wst.lab1.ws.ExterminatusEntity;
import ru.ifmo.wst.lab1.ws.ExterminatusService;
import ru.ifmo.wst.lab1.ws.ExterminatusServiceService;

import java.util.List;

public class ConsoleClient {
    @SneakyThrows
    public static void main(String[] args) {
        ExterminatusServiceService exterminatusService = new ExterminatusServiceService();
        ExterminatusService service = exterminatusService.getExterminatusServicePort();
        List<ExterminatusEntity> filter = service.filter(null, "инк", null, null, null, null);
        System.out.println(filter);
    }
}
