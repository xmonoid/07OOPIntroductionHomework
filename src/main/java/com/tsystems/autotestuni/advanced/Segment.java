package com.tsystems.autotestuni.advanced;

import com.tsystems.autotestuni.basic.Point;

public class Segment {

    final Point one;
    final Point two;

    Segment(Point one, Point two) {
        this.one = one;
        this.two = two;
    }

    public double calculateLength() {
        return Math.sqrt(
                Math.pow( two.getX() - one.getX() ,2)
                        + Math.pow( two.getY() - one.getY() ,2) );
    }
}
