package com.graphics.assignment7;

public class Intersection {

    private Point location;
    private Vector normal;

    public Intersection(Point location, Vector normal) {
        this.location = location;
        this.normal = normal;
    }

    public Point getLocation() {
        return location;
    }

    public Vector getNormal() {
        return normal;
    }


}