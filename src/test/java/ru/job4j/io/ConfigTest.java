package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }

    @Test
    void whenSomeLinesAreEmpty() {
        String path = "./data/emptylines.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("age")).isEqualTo("32");
    }

    @Test
    void whenThereAreMoreEquals() {
        String path = "./data/additionalequals.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Sergei=");
    }

    @Test
    void whenBrokenTemplate() {
        String path = "./data/brokentemplate.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Key or value isEmpty");
    }
}