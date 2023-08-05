package ru.job4j.io;

import java.io.*;
import java.util.List;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zipAll = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path source : sources) {
                File file = source.toFile();
                zipAll.putNextEntry(new ZipEntry(file.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(file))) {
                    zipAll.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void validate(ArgsName arg) {
        File file = new File(arg.get("d"));
        if (!Files.isDirectory(file.toPath())) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.toPath()));
        }
        if (!arg.get("e").startsWith(".")) {
            throw new IllegalArgumentException("The argument should starts with .");
        }
        if (!arg.get("o").endsWith(".zip")) {
            throw new IllegalArgumentException("The argument should ends with .zip");
        }
    }

    public static void main(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("The argument has the wrong template");
        }
        ArgsName arg = ArgsName.of(args);
        validate(arg);
        Predicate<Path> condition = path -> !path.toFile().getName().endsWith(arg.get("e"));

        try {
            List<Path> paths = Search.search(Paths.get(arg.get("d")), condition);
            new Zip().packFiles(paths, new File(arg.get("o")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
