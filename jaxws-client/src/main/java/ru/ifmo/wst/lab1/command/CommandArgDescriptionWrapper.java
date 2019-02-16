package ru.ifmo.wst.lab1.command;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CommandArgDescriptionWrapper<T, C> implements CommandArgDescription<T, C> {
    protected final CommandArgDescription<T, C> wrapped;

    @Override
    public String getName() {
        return wrapped.getName();
    }

    @Override
    public String getDescription() {
        return wrapped.getDescription();
    }

    @Override
    public String getErrorMessage() {
        return wrapped.getErrorMessage();
    }

    @Override
    public T convertFromString(String arg) {
        return wrapped.convertFromString(arg);
    }

    @Override
    public void acceptConverted(T converted, C consumer) {
        wrapped.acceptConverted(converted, consumer);
    }
}
