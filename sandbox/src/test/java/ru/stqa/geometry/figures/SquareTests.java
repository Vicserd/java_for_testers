package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SquareTests {
    @Test
    void canCalculateArea() {
        double result = Square.sqareArea(5.0);
        Assertions.assertEquals(25.0, result);
    }
}
