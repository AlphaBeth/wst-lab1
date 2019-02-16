package ru.ifmo.wst.lab1;

import lombok.SneakyThrows;
import ru.ifmo.wst.lab1.command.Command;
import ru.ifmo.wst.lab1.command.CommandArgDescription;
import ru.ifmo.wst.lab1.command.args.DateArg;
import ru.ifmo.wst.lab1.command.args.EmptyStringToNull;
import ru.ifmo.wst.lab1.command.args.LongArg;
import ru.ifmo.wst.lab1.command.args.StringArg;
import ru.ifmo.wst.lab1.command.CommandInterpreter;
import ru.ifmo.wst.lab1.ws.client.ExterminatusEntity;
import ru.ifmo.wst.lab1.ws.client.ExterminatusService;
import ru.ifmo.wst.lab1.ws.client.ExterminatusServiceService;
import ru.ifmo.wst.lab1.ws.client.Filter;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.BindingProvider;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static java.util.Arrays.asList;

public class ConsoleClient {
    @SneakyThrows
    public static void main(String[] args) {
        ExterminatusServiceService exterminatusService = new ExterminatusServiceService();
        ExterminatusService service = exterminatusService.getExterminatusServicePort();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BindingProvider bindingProvider = (BindingProvider) service;
        String endpointUrl;
        endpointUrl = "http://localhost:8080/deployment-jaxws-1.0/ExterminatusServiceService";
        System.out.print("Enter endpoint url (or empty string for default " + endpointUrl + ")\n> ");
        String line = bufferedReader.readLine();
        if (!line.trim().isEmpty()) {
            endpointUrl = line.trim();
        }
        bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointUrl);


        Command<Void> infoCommand = new Command<>("info", "Print help for commands");
        Command<Box<String>> changeEndpointAddressCommand = new Command<>("endpoint", "Changes endpoint address",
                asList(
                        new StringArg<>("Not a string", "url", "New exterminatus endpoint url", Box::setValue)
                ), Box::new
        );
        Command<Void> findAllCommand = new Command<>("findAll", "Return list of all exterminatus entities");
        Command<Filter> filterCommand = new Command<>("filter",
                "Filter exterminatus entities by column values (ignore case contains for strings), empty values are ignored",
                asList(
                        toNull(new LongArg<>("id", "Exterminatus id", Filter::setId)),
                        toNull(new StringArg<>("initiator", "Initiator name", Filter::setInitiator)),
                        toNull(new StringArg<>("reason", "Reason of exterminatus", Filter::setReason)),
                        toNull(new StringArg<>("method", "Method of exterminatus", Filter::setMethod)),
                        toNull(new StringArg<>("planet", "Exterminated planet", Filter::setPlanet)),
                        toNull(new DateArg<>("date", "Date of exterminatus", (filter, date) -> filter.setDate(fromDate(date))))
                ),
                Filter::new);
        Command<Void> exitCommand = new Command<>("exit", "Exit application");

        CommandInterpreter commandInterpreter = new CommandInterpreter(() -> readLine(bufferedReader),
                System.out::print, asList(infoCommand, changeEndpointAddressCommand, findAllCommand, filterCommand, exitCommand),
                "No command found",
                "Enter command", "> ");

        commandInterpreter.info();

        while (true) {
            Pair<Command, Object> withArg = commandInterpreter.readCommand();
            if (withArg == null) {
                continue;
            }
            Command command = withArg.getLeft();
            if (command.equals(findAllCommand)) {
                List<ExterminatusEntity> all = service.findAll();
                System.out.println("Result of operation:");
                all.forEach(ee -> System.out.println(exterminatusToString(ee)));
            } else if (command.equals(filterCommand)) {
                Filter filterArg = (Filter) withArg.getRight();
                List<ExterminatusEntity> filterRes = service.filter(filterArg.getId(), filterArg.getInitiator(), filterArg.getReason(), filterArg.getMethod(),
                        filterArg.getPlanet(), filterArg.getDate());
                System.out.println("Result of operation:");
                filterRes.forEach(ee -> System.out.println(exterminatusToString(ee)));
            } else if (command.equals(infoCommand)) {
                commandInterpreter.info();
            } else if (command.equals(exitCommand)) {
                break;
            } else if (command.equals(changeEndpointAddressCommand)) {
                @SuppressWarnings("unchecked")
                Box<String> arg = (Box<String>) withArg.getRight();
                String newUrl = arg.getValue();
                bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, newUrl);
            }
        }
    }

    private static <T, C> CommandArgDescription<T, C> toNull(CommandArgDescription<T, C> commandArg) {
        return new EmptyStringToNull<>(commandArg);
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

    private static String exterminatusToString(ExterminatusEntity ee) {
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
