package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {
    /*@Test
    void canCalculateAreaTriangle() {
        var t = new Triangle(3.0,4.0,5.0);
        double result = t.areaTriangle();
        Assertions.assertEquals(6.0, result);}


    @Test
    void canCalculateAreaTriangle() {
    Assertions.assertEquals(39.18146883413127, new Triangle(9.0,9.0,11.0).areaTriangle());
    }


    @Test
    void canCalculatePerimeterTriangle() {
    Assertions.assertEquals(12.0,  new Triangle(3.0,4.0,5.0).perimeter());
    }
*/
    @Test
    void cannotCreateTriangleWithNegativeSide_a(){
        try {
            new Triangle(-7.0, 3.0, 6.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception){
            System.out.println("Значение стороны А не может быть отрицательным");
            System.out.println(exception);
        }
    }
    @Test
    void cannotCreateTriangleWithNegativeSide_b(){
        try {
            new Triangle(7.0, -3.0, 6.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception){
            System.out.println("Значение стороны В не может быть отрицательным");
            System.out.println(exception);
        }
    }
    @Test
    void cannotCreateTriangleWithNegativeSide_c(){
        try {
            new Triangle(7.0, 3.0, -6.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception){
            System.out.println("Значение стороны С не может быть отрицательным");
            System.out.println(exception);
        }
    }
   @Test
    void violatedInequalityTriangle () {
       try {
           new Triangle(10.0, 6.0, 3.0);
           Assertions.fail();
       } catch (IllegalArgumentException exception){
           System.out.println("Нарушено неравенство треугольника");
           System.out.println(exception);
       };
   }

}

