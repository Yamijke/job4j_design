package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        boolean stat = true;
        List<String> log = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello, write your question");
        String scan = scanner.nextLine();

        List<String> phrases = readPhrases();

        while (!scan.equals(OUT)) {
            log.add(scan);
            if (scan.equals(STOP)) {
                stat = false;
            } else if (scan.equals(CONTINUE)) {
                stat = true;
            } else if (stat) {
                String answer = getRandomPhrase(phrases);
                System.out.println(answer);
                log.add(answer);
            }
            scan = scanner.nextLine();
        }
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader buf = new BufferedReader(new FileReader(botAnswers))) {
            String line;
            while ((line = buf.readLine()) != null) {
                rsl.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    private String getRandomPhrase(List<String> phrases) {
        return phrases.get(new Random().nextInt(phrases.size()));
    }

    private void saveLog(List<String> log) {
        try (PrintWriter prnt = new PrintWriter(new FileWriter(path))) {
            for (String line : log) {
                prnt.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat(
                "C:\\Projects\\job4j_design\\searchfiles\\Random dialog.txt",
                "C:\\Projects\\job4j_design\\searchfiles\\Random answers.txt");
        cc.run();
    }
}
