package ru.job4j.map;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        Map<User, Object> map = new HashMap<>();
        Calendar birthday = Calendar.getInstance();
        User user = new User("Alex", 30, birthday);
        User user1 = new User("Alex", 30, birthday);
        map.put(user, new Object());
        map.put(user1, new Object());
        System.out.println(map);
    }
}
