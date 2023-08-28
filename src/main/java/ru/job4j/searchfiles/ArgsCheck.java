package ru.job4j.searchfiles;

import java.util.HashMap;
import java.util.Map;

public class ArgsCheck {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) throws IllegalArgumentException {
        if (values.get(key) == null) {
            throw new IllegalArgumentException("This key: '" + key + "' is missing");
        }
        return values.get(key);
    }

    public void split(String[] args) throws IllegalArgumentException {
        for (String arg : args) {
            validateArg(arg);
            if (arg.startsWith("-")) {
                arg = arg.substring(1);
            }
            String[] parts = arg.split("=", 2);
            if (parts.length < 2) {
                throw new IllegalArgumentException("The argument has wrong template");
            }
            String key = parts[0];
            String value = parts[1];
            values.put(key, value);
        }
    }

    private static void validateArg(String arg) {
        String[] parts = arg.split("=", 2);
        if (arg.startsWith("-=")) {
            throw new IllegalArgumentException("Error: This argument '" + arg + "' does not contain a key");
        }
        if (arg.endsWith("=") && parts[1].isEmpty()) {
            throw new IllegalArgumentException("Error: This argument '" + arg + "' does not contain a value");
        }
        if (!arg.contains("=")) {
            throw new IllegalArgumentException("Error: This argument '" + arg + "' does not contain an equal sign");
        }
    }

    public static ArgsCheck of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        ArgsCheck names = new ArgsCheck();
        names.split(args);
        return names;
    }
}
