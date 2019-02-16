package ru.ifmo.wst.lab1.client;

import lombok.Getter;
import lombok.SneakyThrows;
import ru.ifmo.wst.lab1.Box;
import ru.ifmo.wst.lab1.command.Command;
import ru.ifmo.wst.lab1.command.CommandArg;
import ru.ifmo.wst.lab1.command.CommandArgDescription;
import ru.ifmo.wst.lab1.command.CommandInterpreter;
import ru.ifmo.wst.lab1.command.args.DateArg;
import ru.ifmo.wst.lab1.command.args.EmptyStringToNull;
import ru.ifmo.wst.lab1.command.args.LongArg;
import ru.ifmo.wst.lab1.command.args.StringArg;
import ru.ifmo.wst.lab1.ws.client.ExterminatusEntity;
import ru.ifmo.wst.lab1.ws.client.ExterminatusService;
import ru.ifmo.wst.lab1.ws.client.Filter;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.BindingProvider;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static java.util.Arrays.asList;

public class ExterminatusServiceConsoleClient {
    private final CommandInterpreter commandInterpreter;
    @Getter
    private boolean exit = false;
    private ExterminatusService service;

    public ExterminatusServiceConsoleClient(ExterminatusService service) {
        this.service = service;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Command<Void> infoCommand = new Command<>("info", "Print help for commands", (arg) -> this.info());
        Command<Box<String>> changeEndpointAddressCommand = new Command<>("endpoint", "Changes endpoint address",
                Arrays.asList(
                        new CommandArg<>(new StringArg("url", "New exterminatus endpoint url"), Box::setValue)
                ), Box::new, this::changeEndpointUrl
        );
        Command<Void> findAllCommand = new Command<>("findAll", "Return list of all exterminatus entities", (arg) -> this.findAll());
        Command<Filter> filterCommand = new Command<>("filter",
                "Filter exterminatus entities by column values (ignore case contains for strings), empty values are ignored",
                asList(
                        new CommandArg<>(toNull(new LongArg("id", "Exterminatus id")), Filter::setId),
                        new CommandArg<>(toNull(new StringArg("initiator", "Initiator name")), Filter::setInitiator),
                        new CommandArg<>(toNull(new StringArg("reason", "Reason of exterminatus")), Filter::setReason),
                        new CommandArg<>(toNull(new StringArg("method", "Method of exterminatus")), Filter::setMethod),
                        new CommandArg<>(toNull(new StringArg("planet", "Exterminated planet")), Filter::setPlanet),
                        new CommandArg<>(toNull(new DateArg("date", "Date of exterminatus")), (filter, date) -> filter.setDate(fromDate(date)))
                ),
                Filter::new, this::filter);
        Command<Void> exitCommand = new Command<>("exit", "Exit application", (arg) -> this.exit = true);
        this.commandInterpreter = new CommandInterpreter(() -> readLine(bufferedReader),
                System.out::print, asList(infoCommand, changeEndpointAddressCommand, findAllCommand, filterCommand, exitCommand),
                "No command found",
                "Enter command", "> ");

    }

    public void start() {
        while (!exit) {
            commandInterpreter.readCommand();

        }
    }

    public void changeEndpointUrl(Box<String> box) {
        changeEndpointUrl(box.getValue());
    }

    public void changeEndpointUrl(String endpointUrl) {
        ((BindingProvider) service).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointUrl);
    }

    public void findAll() {
        List<ExterminatusEntity> all = service.findAll();
        System.out.println("Result of operation:");
        all.forEach(ee -> System.out.println(exterminatusToString(ee)));
    }

    public void filter(Filter filterArg) {
        List<ExterminatusEntity> filterRes = service.filter(filterArg.getId(), filterArg.getInitiator(), filterArg.getReason(), filterArg.getMethod(),
                filterArg.getPlanet(), filterArg.getDate());
        System.out.println("Result of operation:");
        filterRes.forEach(ee -> System.out.println(exterminatusToString(ee)));
    }

    public void info() {
        commandInterpreter.info();
    }


    @SneakyThrows
    private static String readLine(BufferedReader reader) {
        return reader.readLine();
    }

    @SneakyThrows
    private static XMLGregorianCalendar fromDate(Date date) {
        if (date == null) {
            return null;
        }
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(
                gregorianCalendar);
    }

    private static <T> CommandArgDescription<T> toNull(CommandArgDescription<T> commandArg) {
        return new EmptyStringToNull<>(commandArg);
    }

    private static String exterminatusToString(ru.ifmo.wst.lab1.ws.client.ExterminatusEntity ee) {
        return "ExterminatusEntity{" +
                "date=" + ee.getDate() +
                ", id=" + ee.getId() +
                ", initiator='" + ee.getInitiator() + '\'' +
                ", method='" + ee.getMethod() + '\'' +
                ", planet='" + ee.getPlanet() + '\'' +
                ", reason='" + ee.getReason() + '\'' +
                '}';

    }
}
