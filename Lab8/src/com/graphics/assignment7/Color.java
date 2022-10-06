package com.graphics.assignment7;

import org.json.JSONObject;

public class Color {

    private double r;
    private double g;
    private double b;

    public Color() {
    }

    public Color(JSONObject color) {
        this.r = color.getDouble("r");
        this.g = color.getDouble("g");
        this.b = color.getDouble("b");
    }

    public Color(double r, double g, double b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    private static int truncateColor(double color) {
        if (color < 0) {
            return 0;
        }
        if (color > 255) {
            return 255;
        }
        return (int) color;
    }

    public int toRgb() {
        return (truncateColor(r) << 16) | (truncateColor(g) << 8) | (truncateColor(b));
    }

    public double getR() {
        return r;
    }

    public void addR(double r) {
        this.r += r;
    }

    public double getG() {
        return g;
    }

    public void addG(double g) {
        this.g += g;
    }

    public double getB() {
        return b;
    }

    public void addB(double b) {
        this.b += b;
    }
}