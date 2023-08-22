package ru.job4j.serialization;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Pojo {
    public static void main(String[] args) {
        JSONObject jsonAdditional = new JSONObject();
        jsonAdditional.put("attachment", "attachment");
        jsonAdditional.put("bonus", "bonus");

        List<String> list = new ArrayList<>();
        list.add("Alex");
        list.add("Lisa");
        JSONArray jsonOwnerses = new JSONArray(list);

        final Car car = new Car(true, 11, new Additional("additional", "bonus"), "Alex", "Lisa");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("old", car.isOld());
        jsonObject.put("age", car.getAge());
        jsonObject.put("additional", jsonAdditional);
        jsonObject.put("ownerses", jsonOwnerses);

        System.out.println(jsonObject.toString());
        System.out.println(new JSONObject(car).toString());
    }
}
