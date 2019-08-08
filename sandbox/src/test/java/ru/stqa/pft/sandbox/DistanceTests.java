package ru.stqa.pft.sandbox;

import org.testng.annotations.Test;

public class SquareTests {

    @Test
    public void testArea () {
        Point p1 = new Point(0,0);
        Point p2 = new Point(-3,10.5);

        assert p1.distance(p2) == 10.920164833920778;
    }
}
