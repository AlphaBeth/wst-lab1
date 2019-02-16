package ru.ifmo.wst.lab1.command.args;

import ru.ifmo.wst.lab1.command.AbstractCommandArgDescription;

import java.util.function.BiConsumer;

public class StringArg<C> extends AbstractCommandArgDescription<String, C> {
    public StringArg(String errorMessage, String name, String description, BiConsumer<C, String> consumer) {
        super(errorMessage, name, description, consumer);
    }

    public StringArg(String name, String description, BiConsumer<C, String> consumer) {
        this("Not a string", name, description, consumer);
    }

    @Override
    public String convertFromString(String arg) {
        return arg;
    }
}
