package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("data/even.txt")) {
            Scanner scanner = new Scanner(in);
            while (scanner.hasNextInt()) {
                int num = scanner.nextInt();
                if (num % 2 == 0) {
                    System.out.println(num + " is even");
                } else {
                    System.out.println(num + " is odd");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
