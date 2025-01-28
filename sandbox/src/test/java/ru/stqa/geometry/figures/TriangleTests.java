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
*/
    @Test
    void testEquality() {
        var t1 = new Triangle(3.0, 3.0, 3.0);
        var t2 = new Triangle(3.0, 3.0, 3.0);

        Assertions.assertEquals(t1, t2);
    }
    @Test
    void testEquality2(){
        var t1 = new Triangle(5.0,4.0, 3.0);
        var t2 = new Triangle(5.0,3.0, 4.0);
        var t3 = new Triangle(4.0,5.0, 3.0);
        var t4 = new Triangle(4.0,3.0, 5.0);
        var t5 = new Triangle(3.0,4.0, 5.0);
        var t6 = new Triangle(3.0,5.0, 4.0);

        Assertions.assertEquals(t1,t2);
        Assertions.assertEquals(t3,t4);
        Assertions.assertEquals(t5,t6);
        Assertions.assertEquals(t1,t3);
        Assertions.assertEquals(t1,t4);
        Assertions.assertEquals(t1,t5);
        Assertions.assertEquals(t1,t6);
        Assertions.assertEquals(t2,t3);
        Assertions.assertEquals(t2,t4);
        Assertions.assertEquals(t2,t5);
        Assertions.assertEquals(t2,t6);
        Assertions.assertEquals(t3,t5);
        Assertions.assertEquals(t3,t6);
        Assertions.assertEquals(t4,t5);
        Assertions.assertEquals(t4,t6); //будет ли правильным такой перебор или же на каждый перебор нужен отдельный тест?
    }
}

