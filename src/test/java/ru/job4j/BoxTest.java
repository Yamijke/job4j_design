package ru.job4j;

import net.bytebuddy.build.ToStringPlugin;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Tetrahedron");
    }

    @Test
    void numberOfVerticesIs8() {
        Box box = new Box(8, 10);
        int result = box.getNumberOfVertices();
        assertThat(result).isEqualTo(8);
    }

    @Test
    void numberOfVerticesIs4() {
        Box box = new Box(4, 10);
        int result = box.getNumberOfVertices();
        assertThat(result).isEqualTo(4);
    }

    @Test
    void theFigureIsExist() {
        Box box = new Box(4, 10);
        boolean result = box.isExist();
        assertThat(result).isEqualTo(true);
    }

    @Test
    void theFigureIsNotExist() {
        Box box = new Box(-1, 10);
        boolean result = box.isExist();
        assertThat(result).isEqualTo(false);
    }

    @Test
    void theAreaOfTetrahedron() {
        Box box = new Box(4, 10);
        double expected = box.getArea();
        assertThat(expected).isEqualTo(173.20508075688772);

    }

    @Test
    void theAreaOfCube() {
        Box box = new Box(8, 20);
        double expected = box.getArea();
        assertThat(expected).isEqualTo(2400D);
    }
}