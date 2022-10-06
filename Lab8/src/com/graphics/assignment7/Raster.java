package com.graphics.assignment7;

import org.json.JSONObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Raster {

    protected int width;
    protected int height;
    protected double[] raster;

    public Raster(int width, int height) {
        this.width = width;
        this.height = height;
        raster = new double[width * height * 3];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


    public Color getPixelColor(int x, int y) {
        int index = getIndex(x, y);
        return new Color(raster[index + 0], raster[index + 1], raster[index + 2]);
    }


    public void setPixelColor(Color color, int x, int y) {
        int index = getIndex(x, y);
        raster[index + 0] = (float) color.getR();
        raster[index + 1] = (float) color.getG();
        raster[index + 2] = (float) color.getB();

    }

    protected final int getIndex(int inX, int inY) {

        return ((height - 1 - inY) * width + inX) * 3;
    }

    public BufferedImage create() {
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = this.getPixelColor(x, y);
                int rgb = color.toRgb();
                result.setRGB(x, (height - 1 - y), rgb);
            }
        }
        return result;
    }

    public void writeImage(JSONObject object, BufferedImage image) throws IOException {
        String filename = object.getString("filename");
        ImageIO.write(image, "png", new File(filename));
    }
}
