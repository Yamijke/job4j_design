package ru.job4j.aaatest;

import java.util.Arrays;

public class test {
    public static void main(String[] args) {
        int[] data = {1, 2, 3, 4, 5};
        for (int i = 0; i < data.length; i++) {
            for (int j = data.length - (1 + i); j > i; j--) {
                int change = data[i];
                data[i] = data[j];
                data[j] = change;
                break;
            }
        }
        System.out.println(Arrays.toString(data));
    }
}
