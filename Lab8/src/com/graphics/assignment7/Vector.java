package com.graphics.assignment7;


import org.json.JSONObject;

public class Vector extends Point {

    public Vector(Point other) {
        super(other);
    }

    public Vector() {

    }

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector(JSONObject object) {
        super(object);
    }

    public double dot(Vector other) {

        return x * other.x + y * other.y + z * other.z;
    }

    public void normalize() {
        double l = length();
        if (l == 0) {
            return;
        }
        this.x /= l;
        this.y /= l;
        this.z /= l;
    }

    public double length() {
        return Math.sqrt(x * x + y * y + z * z);
    }


    public void subtract(Point p2) {
        this.x -= p2.x;
        this.y -= p2.y;
        this.z -= p2.z;

    }

    public static Vector scale(Vector other, double scale) {
        Vector result = new Vector(other);
        result.x *= scale;
        result.y *= scale;
        result.z *= scale;
        return result;
    }


}