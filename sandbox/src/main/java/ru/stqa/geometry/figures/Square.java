package ru.stqa.geometry.figures;

public class Square {
    public static void printSqareArea(double side){
        String text = String.format("Площадь квадрата со стороной %f = %f", side,sqareArea(side));
        System.out.println(text);
    }

    private static double sqareArea(double a) {
        return a * a;
    }
}
