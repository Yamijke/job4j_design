package ru.job4j.serialization;

import java.util.Arrays;

public class Car {
    private final boolean old;
    private final int age;
    private final Additional additional;
    private final String[] owners;

    public Car(boolean old, int age, Additional additional, String[] owners) {
        this.old = old;
        this.age = age;
        this.additional = additional;
        this.owners = owners;
    }

    @Override
    public String toString() {
        return "Car{"
                + "old=" + old
                + ", age=" + age
                + ", additional=" + additional
                + ", owners=" + Arrays.toString(owners) +
                '}';
    }
}
