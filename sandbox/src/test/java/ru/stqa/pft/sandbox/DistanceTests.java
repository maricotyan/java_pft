package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DistanceTests {

    @Test
    public void firstTest() {
        Point p1 = new Point(0,0);
        Point p2 = new Point(-3,10.5);

        Assert.assertEquals(p1.distance(p2), 10.920164833920778);
    }

    @Test
    public void secondTest() {
        Point p1 = new Point(2,2);
        Point p2 = new Point(2,2);

        Assert.assertEquals(p1.distance(p2), 0.0);
    }

    @Test
    public void thirdTest() {
        Point p1 = new Point(4.9e-324,4.9e-324);
        Point p2 = new Point(1.7e+308,1.7e+308);

        Assert.assertEquals(p1.distance(p2), 1.7e+308);
    }
}
