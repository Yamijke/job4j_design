package ru.job4j.io;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class LogFilter {
    private final String file;

    public LogFilter(String file) {
        this.file = file;
    }

    public List<String> filter() {
        List<String> res = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = in.readLine()) != null) {
                String[] parts = line.split(" ");
                if (Integer.parseInt(parts[parts.length - 2]) == 404) {
                    res.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter("data/log.txt");
        logFilter.filter()
                .forEach(System.out::println);
    }
}
