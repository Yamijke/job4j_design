package ru.job4j.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Json {
    public static void main(String[] args) {
        final Car car = new Car(true, 11, new Additional("attachment", "bonus"),
                new String[]{"Alex", "Lisa"});
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(car));
        final String carJson =
                "{"
                        + "\"old\":true,"
                        + "\"age\":11,"
                        + "\"additional\":"
                        + "{"
                        + "\"attachment\":\"attachment\","
                        + "\"bonus\":\"bonus\""
                        + "},"
                        + "\"owners\":"
                        + "[\"Alex\",\"Lisa\"]"
                        + "}";
        final Car carMod = gson.fromJson(carJson, Car.class);
        System.out.println(carMod);
    }
}
