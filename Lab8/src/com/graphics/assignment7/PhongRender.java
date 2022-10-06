package com.graphics.assignment7;

import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class PhongRender {

    private final Scene scene;

    public PhongRender(Scene scene) {
        this.scene = scene;
    }

    public static void main(String[] args) throws IOException {
        args = new String[1];
        args[0] = "sphere.json";
        String fileName = args[0];

        // Parse the input file
        Scene scene = loadScene(fileName);
        PhongRender phongRender = new PhongRender(scene);
        phongRender.render();

        JFrame frame = new JFrame() {
            @Override
            public void paint(Graphics g) {
                Raster raster = scene.getRaster();
                BufferedImage image = raster.create();
                g.drawImage(image, 0, 0, null);
                try {
                    raster.writeImage(new JSONObject(Files.readString(Path.of(fileName))), image);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        frame.setBounds(0, 0, scene.getRaster().getWidth(), scene.getRaster().getHeight());
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    private static Scene loadScene(String fileName) throws IOException {
        String content = Files.readString(Path.of(fileName));
        JSONObject scene = new JSONObject(content);
        return new Scene(scene);
    }


    public void render() {
        long startTime = System.currentTimeMillis();
        List<Pixel> pixels = new ArrayList<>();
        Raster raster = scene.getRaster();
        int width = raster.getWidth();
        int height = raster.getHeight();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixels.add(new Pixel(x, y));
            }
        }
        trace(scene, pixels);

        long totalTime = (System.currentTimeMillis() - startTime);
        System.out.print("Completed in [" + (totalTime / 1000.0) + "] seconds.");
    }

    public Color getPixelColor(Scene scene, Ray ray) {
        List<Sphere> spheres = scene.getSpheres();
        Intersection rightIntersection = null;
        double z = Double.MIN_VALUE;
        for (Sphere sphere : spheres) {
            Intersection intersectInfo = sphere.getIntersection(ray);
            if (intersectInfo != null) {
                if (z < intersectInfo.getLocation().z) {
                    z = intersectInfo.getLocation().z;
                    rightIntersection = intersectInfo;
                }
            }
        }
        if (rightIntersection!=null) {
            return scene.getPhong().getPointColor(scene, rightIntersection);
        }
        return new Color(0, 0, 100);
    }

    public void trace(Scene scene, List<Pixel> pixels) {
        Raster raster = scene.getRaster();
        Observer observer = scene.getObserver();
        int width = raster.getWidth();
        int height = raster.getHeight();
        double invHeight = 1.0 / height;
        double invWidth = 1.0 / width;

        for (Pixel pixel : pixels) {
            Ray ray = observer.getRay((pixel.getX() + 0.5) * invWidth, (pixel.getY() + 0.5) * invHeight);
            Color rayColor = getPixelColor(scene, ray);
            pixel.setColor(rayColor);
        }
        for (Pixel pixel : pixels) {
            raster.setPixelColor(pixel.getColor(), (int) pixel.getX(), (int) pixel.getY());
        }
    }

}
