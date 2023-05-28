package ru.job4j;

import net.bytebuddy.build.ToStringPlugin;
import org.assertj.core.data.Offset;
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
        assertThat(expected).isCloseTo(173.2d, Offset.offset(0.1));

    }

    @Test
    void theAreaOfCube() {
        Box box = new Box(8, 20);
        double expected = box.getArea();
        assertThat(expected).isCloseTo(2400, Offset.offset(0.001));
    }
}