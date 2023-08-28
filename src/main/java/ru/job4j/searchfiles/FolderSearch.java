package ru.job4j.searchfiles;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class FolderSearch extends SimpleFileVisitor<Path> {
    private Predicate<Path> predicate;
    private List<Path> paths;

    public FolderSearch(Predicate<Path> predicate) {
        this.predicate = predicate;
        this.paths = new ArrayList<>();
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (predicate.test(file)) {
            paths.add(file);
        }
        return FileVisitResult.CONTINUE;
    }

    public List<Path> getPaths() {
        return paths;
    }

    public void setPredicate(Predicate<Path> predicate) {
        this.predicate = predicate;
    }
}
