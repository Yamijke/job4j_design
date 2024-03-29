package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.*;
import java.nio.file.Path;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AnalysisTest {

    @Test
    void tempTest(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.log").toFile();
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("300 10:59:01");
            out.println("500 11:01:02");
            out.println("200 11:02:02");
        }
        File target = tempDir.resolve("target.csv").toFile();
        Analysis test = new Analysis();
        test.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader buf = new BufferedReader(new FileReader(target))) {
            buf.lines().forEach(rsl::append);
        }
        assertThat("10:57:01;10:59:01;11:01:02;11:02:02;").hasToString(rsl.toString());
    }
}