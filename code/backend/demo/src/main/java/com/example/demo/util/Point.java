package com.example.demo.util;

import java.util.Objects;

/**
 * @author Zhe Li
 * @date 2020/05/10
 */
public class Point {
    private double x;

    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Double.compare(point.x, x) == 0 &&
                Double.compare(point.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public static double getSlope(Point p1, Point p2) {
        if(p1.getX() == p2.getX()) {
            return Double.POSITIVE_INFINITY;
        }
        return (p1.getY()- p2.getY())/(p1.getX() - p2.getX());

    }
}
