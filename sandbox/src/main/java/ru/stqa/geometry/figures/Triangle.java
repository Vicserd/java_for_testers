package ru.stqa.geometry.figures;

public class Triangle {
    public static void printTriangleArea(double a, double b, double c){
        var text = String.format("Площадь треугольника со сторонами %f и  %f и %f = %f", a, b,c, TriangleArea(a, b, c));
        System.out.println(text);
    }

    private static Object TriangleArea(double a, double b, double c) {
        double p = (a + b + c) / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

};
