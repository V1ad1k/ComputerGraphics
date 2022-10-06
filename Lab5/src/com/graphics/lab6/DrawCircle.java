package com.graphics.lab6;

import javax.xml.crypto.dsig.Transform;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class DrawCircle extends DrawShape<Ellipse2D.Double> {
    private static final int DEFAULT_RADIUS = 50;

    public DrawCircle(Point center, Color color) {
        super(center, new Ellipse2D.Double(0, 0, DEFAULT_RADIUS, DEFAULT_RADIUS), color);
    }

    @Override
    protected double getWidth() {
        return shape.getWidth();
    }

    @Override
    protected double getHeight() {
        return shape.getHeight();
    }

    @Override
    public boolean isResizeSelected(int x, int y) {
        Point2D center = getCenter();
        double currentRadius = Math.sqrt(Math.pow(x - center.getX(), 2) + Math.pow(y - center.getY(), 2));
        double circleRadius = getHeight() / 2;
        return Math.abs(currentRadius - circleRadius) < SELECTION_GAP;
    }


    @Override
    protected void doResize(double w, double h) {
        shape.height = Math.max(w, h);
        shape.width = Math.max(w, h);
    }

    public boolean isRotationSelected(int x, int y) {
        return false;
    }

    protected void protate(int direction) {
        //do nothing
    }

}
