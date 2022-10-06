package com.graphics.lab6;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.io.Serializable;

public abstract class DrawBase implements Serializable {
    protected static final int RESIZE_LIMIT = 20;
    protected AffineTransform movement = new AffineTransform();
    protected AffineTransform rotation = new AffineTransform();

    protected static final int SELECTION_GAP = 15;

    private double ROTATION_ANGLE = 0.01;

    public DrawBase() {
    }

    private Point2D getTransformedPoint(double x, double y) {
        AffineTransform transform = getTransform();
        return transform.transform(new Point2D.Double(x, y), new Point2D.Double());
    }

    protected Point2D getCenter() {
        return getTransformedPoint(getWidth() / 2, getHeight() / 2);
    }

    public boolean isCenterSelected(int x, int y) {
        Point2D center = getCenter();
        return Math.abs(center.getX() - x) < SELECTION_GAP && Math.abs(center.getY() - y) < SELECTION_GAP;
    }

    public AffineTransform getTransform() {
        AffineTransform result = new AffineTransform(movement);
        result.concatenate(rotation);
        return result;
    }
    public void BringTop() {
        movement.setToTranslation(0, 0);
    }

    public void PushBottom() {
        movement.setToTranslation(getWidth(), getHeight() / 2);
    }

    public void move(double x, double y) {
        movement.setToTranslation(x - getWidth() / 2, y - getHeight() / 2);
    }

    public void pmove(double dx, double dy) {
        movement.translate(dx, dy);
    }

    public Point2D getRotationPoint() {
        return getTransformedPoint(getWidth(), 0);
    }

    public boolean isRotationSelected(int x, int y) {
        Point2D point = getRotationPoint();
        return Math.abs(point.getX() - x) < SELECTION_GAP && Math.abs(point.getY() - y) < SELECTION_GAP;
    }

    public Point2D getResizePoint() {
        return getTransformedPoint(getWidth(), getHeight());
    }

    public boolean isResizeSelected(int x, int y) {
        Point2D point = getResizePoint();
        return Math.abs(point.getX() - x) < SELECTION_GAP && Math.abs(point.getY() - y) < SELECTION_GAP;
    }

    protected void protate(int direction) {
        double dw = getWidth() / 2;
        double dh = getWidth() / 2;
        rotation.rotate(Math.signum(direction) * ROTATION_ANGLE, dw, dh);
    }


    public void rotate(double x, double y) {
        double dw = getWidth() / 2;
        double dh = getHeight() / 2;
        Point2D center = getCenter();
        double theta = Math.atan2(y - center.getY(), x - center.getX()) + Math.PI / 4;
        rotation.setToRotation(theta, dw, dh);
    }

    public void resize(double x, double y) {
        AffineTransform transform = getTransform();
        try {
            Point2D invCoords = rotation.inverseTransform(new Point2D.Double(x, y), new Point2D.Double());
            Point2D invCenter = rotation.inverseTransform(new Point2D.Double(transform.getTranslateX(), transform.getTranslateY()), new Point2D.Double());
            double w = invCoords.getX() - invCenter.getX();
            double h = invCoords.getY() - invCenter.getY();
            if (w > RESIZE_LIMIT && h > RESIZE_LIMIT) {
                doResize(w, h);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected abstract void doResize(double w, double h);

    protected abstract double getWidth();

    protected abstract double getHeight();


    public abstract void draw(Graphics2D g);

}
