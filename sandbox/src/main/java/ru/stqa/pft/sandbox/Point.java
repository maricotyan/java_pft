package ru.stqa.pft.sandbox;

public class Point {
    public double x;
    public double y;

    public Point (double x, double y){
        this.x = x;
        this.y = y;
    }

    public double distance(Point other) {
        double a = Math.pow((this.x - other.x ), 2);
        double b = Math.pow((this.y - other.y ), 2);
        return Math.sqrt (a + b);
    }
}
