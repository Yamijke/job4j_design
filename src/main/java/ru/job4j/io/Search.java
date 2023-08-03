package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        isValid(args);
        Path start = Paths.get(args[0]);
        search(start, path -> path.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    private static void isValid(String[] args) throws IllegalArgumentException {
        if (args.length < 2) {
            throw new IllegalArgumentException("Root folder is null or file extension is missing. Usage ROOT_FOLDER FILE_EXTENSION.");
        }
        Path start = Paths.get(args[0]);
        if (!Files.exists(start) || !Files.isDirectory(start)) {
            throw new IllegalArgumentException(String.format("Not a directory %s", start.toFile().getAbsoluteFile()));
        }
    }
}
