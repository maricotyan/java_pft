package ru.stqa.pft.sandbox;

public class Distance {
    public static void main (String[] args) {
        Point p1 = new Point(4.9e-324,4.9e-324);
        Point p2 = new Point(1.7e+308,1.7e+308);

        System.out.println("Расстояние между точками " + distance(p1, p2));
        System.out.println("Расстояние между точками " + p1.distance(p2));
    }

    public static double distance(Point p1, Point p2) {
        double a = Math.pow((p1.x - p2.x ), 2);
        double b = Math.pow((p1.y - p2.y ), 2);
        return Math.sqrt (a + b);
    }
}
