package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        FilePropertySearch search = new FilePropertySearch();
        Files.walkFileTree(Paths.get(
                "C:\\Projects\\job4j_design\\searchfiles"), search);
        search.printDuplicates();
    }
}
