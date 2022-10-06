package com.graphics.lab6;

import java.awt.*;
import java.awt.geom.AffineTransform;

public abstract class DrawShape<T extends Shape> extends DrawBase {

    protected T shape;
    protected Color color;

    public DrawShape(Point center, T shape, Color color) {
        this.shape = shape;
        this.color = color;
        move(center.x, center.y);
    }


    @Override
    public void draw(Graphics2D g) {
        Color originalColor = g.getColor();
        g.setColor(color);
        g.fill(getShape());
        g.setColor(originalColor);
    }

    public T getShape() {
        return (T) getTransform().createTransformedShape(shape);
    }
}
