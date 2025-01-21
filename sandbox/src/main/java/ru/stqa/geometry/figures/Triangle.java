package ru.stqa.geometry.figures;

public record Triangle (double a, double b, double c) {
    public static void printTriangleArea(Triangle t){
        var text = String.format("Площадь треугольника со сторонами %f и  %f и %f = %f",t.a,t.b,t.c , t.areaTriangle());
        System.out.println(text);
    }

    public double areaTriangle() {
        double p = (a + b + c) / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    public double perimeter() {
        return this.a + this.b + this.c;
    }
};
