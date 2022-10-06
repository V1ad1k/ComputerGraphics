package com.graphics.lab6;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class DrawImage extends DrawBase implements Serializable {
    private transient Image image;
    private transient Image original;

    public DrawImage(Point center, Image image) {
        this.image = getScaledImage(image, image.getWidth(null), image.getHeight(null));
        this.original = getScaledImage(image, image.getWidth(null), image.getHeight(null));
        move(center.x, center.y);
    }


    @Override
    public double getWidth() {
        return image.getWidth(null);
    }

    @Override
    public double getHeight() {
        return image.getHeight(null);
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(image, getTransform(), null);
    }

    @Override
    protected void doResize(double w, double h) {
        image = getScaledImage(original, (int) w, (int) h);
    }

    public static BufferedImage getScaledImage(Image image, int wt, int ht) {
        BufferedImage resizedImg = new BufferedImage(wt, ht, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        //TODO: IMPLEMENT IT YOURSELF, PEOPLE

        return resizedImg;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeInt((int)getWidth());
        out.writeInt((int)getHeight());
        ImageIO.write((BufferedImage) original, "png", out);
        out.flush();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        int w = in.readInt();
        int h = in.readInt();
        original = ImageIO.read(in);
        image = getScaledImage(original, w, h);
    }
}
