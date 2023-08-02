package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilePropertySearch extends SimpleFileVisitor<Path> {
    private final Map<FileProperty, List<Path>> property = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        BasicFileAttributes attributes = Files.readAttributes(file, BasicFileAttributes.class);
        FileProperty newProperty = new FileProperty(attributes.size(), file.getFileName().toString());
        List<Path> paths = property.computeIfAbsent(newProperty, k -> new ArrayList<>());
        paths.add(file);
        return FileVisitResult.CONTINUE;
    }

    public void printDuplicates() {
        for (Map.Entry<FileProperty, List<Path>> check : property.entrySet()) {
            FileProperty key = check.getKey();
            List<Path> value = check.getValue();
            if (value.size() > 1) {
                System.out.println(String.format("%s - %sMb", key.getName(), key.getSize()));
                for (Path path : value) {
                    System.out.println("    " + path.toString());
                }
            }
        }
    }
}
