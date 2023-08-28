package ru.job4j.searchfiles;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.function.Predicate;
import java.util.regex.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.io.*;

/**
 * 1. Программа для поиска файлов.
 * 2. Программа должна искать данные в заданном каталоге и подкаталогах.
 * 3. Имя файла может задаваться, целиком, по маске, по регулярному выражению(не обязательно).
 * 4. Программа должна запускаться с параметрами, например:  -d=c:/ -n=*.?xt -t=mask -o=log.txt
 * 5. Программа должна записывать результат в файл.
 * 6. В программе должна быть валидация ключей и подсказка.
 */

public class FileSearchWriterService {
    public static void main(String[] args) throws IOException {
        validate(args);
        ArgsCheck values = ArgsCheck.of(args);
        Path root = Paths.get(values.get("d"));
        Predicate<Path> searchPredicate = choosePredicate(values);
        FolderSearch folderSearch = new FolderSearch(searchPredicate);
        Files.walkFileTree(root, folderSearch);
        List<Path> foundFiles = folderSearch.getPaths();
        Path outputPath = Paths.get(values.get("o"));
        try (BufferedWriter writer = Files.newBufferedWriter(outputPath)) {
            for (Path foundFile : foundFiles) {
                writer.write(foundFile.toString());
                writer.newLine();
            }
        }
    }

    public static Predicate<Path> choosePredicate(ArgsCheck arg) {
        String fileName = arg.get("n");
        if ("mask".equals(arg.get("t"))) {
            return maskToPredicate(fileName);
        } else if ("name".equals(arg.get("t"))) {
            return path -> path.getFileName().toString().equals(fileName);
        } else if ("regex".equals(arg.get("t"))) {
            Pattern pattern = Pattern.compile(fileName);
            return path -> pattern.matcher(path.getFileName().toString()).matches();
        } else {
            throw new IllegalArgumentException("Unknown search type: " + arg.get("t"));
        }
    }

    private static Predicate<Path> maskToPredicate(String mask) {
        String regex = mask
                .replace(".", "\\.")
                .replace("?", ".")
                .replace("*", ".*");
        Pattern pattern = Pattern.compile(regex);
        return path -> pattern.matcher(path.getFileName().toString()).matches();
    }

    public static void validate(String[] args) {
        if (args[0].length() == 0) {
            throw new IllegalArgumentException("First argument cant be empty");
        }
        if (args.length != 4) {
            throw new IllegalArgumentException("Number of arguments is not valid");
        }
        ArgsCheck values = ArgsCheck.of(args);
        File file = new File(values.get("d"));
        if (!Files.exists(file.toPath())) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.toPath()));
        }
        if (!Files.isDirectory(file.toPath())) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.toPath()));
        }
        if (args[1].length() <= 1) {
            throw new IllegalArgumentException("Second argument length has to be more than 1");
        }
        if (!"mask".equals(values.get("t"))
                && !"name".equals(values.get("t"))
                && !"regex".equals(values.get("t"))) {
            throw new IllegalArgumentException(
                    "Check out the input parameters. It might be \"name\", \"mask\", or \"regex\"");
        }
    }
}
