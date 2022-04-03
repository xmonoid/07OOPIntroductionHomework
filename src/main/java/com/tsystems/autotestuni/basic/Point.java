package com.tsystems.autotestuni.basic;

/**
 * 1. Create a private field "y" of a {@code double} type.
 * 2. Add methods getY() и setY() which should get and set values of "y" respectively.
 * 3. Create a constructor with two parameters, through which fields "x" and "y" get their initial values.
 */
public class Point {

    private double x;
    private double y;

    public Point() {
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }
}
