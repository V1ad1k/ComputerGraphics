package com.graphics.lab6;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class DrawRect extends DrawShape<Rectangle2D.Double> {
    private static final double DEFAULT_HEIGHT = 50;
    private static final double DEFAULT_WIDTH = 100;

    public DrawRect(Point center, Color color) {
        super(center, new Rectangle2D.Double(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT), color);
    }

    @Override
    protected void doResize(double w, double h) {
        shape.width = w;
        shape.height = h;
    }

    @Override
    public double getWidth() {
        return shape.getWidth();
    }

    @Override
    public double getHeight() {
        return shape.getHeight();
    }

}
