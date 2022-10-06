import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
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

                g.drawImage(scene.getRaster().create(), 0, 0, null);
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
        //todo: measure render time
    }

    public Color getPixelColor(Scene scene, Ray ray) {
        Intersection intersectInfo = scene.getSphere().getIntersection(ray);
        if (intersectInfo == null) {
            return new Color();
        }

        return scene.getPhong().getPointColor(scene, intersectInfo);
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
