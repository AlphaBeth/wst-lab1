package ru.ifmo.wst.lab1.util.db;

public interface Condition {
    String build();
    Object getValue();
    Class<?> getType();
    String getColumnName();
}
