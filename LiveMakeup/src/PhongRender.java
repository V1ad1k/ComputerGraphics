import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;

public class PhongRender {

    private final Object3d[] scene;

    public PhongRender(Object3d[] scene) {
        this.scene = scene;
    }
    private Raster raster = new Raster(1000,800);
    List<String> distance = Arrays.asList("150");
    private Observer observer = new Observer(distance);
    public static void main(String[] args) throws Exception {
//        args = new String[1];
//        args[0] = "scene2.txt";
//        String fileName = args[0];
//
//        Scene scene = new Scene(fileName);
//        PhongRender phongRender = new PhongRender(scene);
//        phongRender.render();
//
//        JFrame frame = new JFrame() {
//            @Override
//            public void paint(Graphics g) {
//                Raster raster = scene.getRaster();
//                BufferedImage image = raster.create();
//                g.drawImage(image, 0, 0, null);
//                try {
//                    raster.writeImage(image);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//        frame.setBounds(0, 0, scene.getRaster().getWidth(), scene.getRaster().getHeight());
//        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//        frame.setVisible(true);



        // Parse the OBJ file and convert it to array of Triangles
        Object3d[] scene2 = ObjLoader.parseFile(new File("renders/seahorse.obj"));
        // Create a camera at position (0, 2, -6), facing forward
//        Vector3 cameraPosition = new Vector3(0, 2, -6);
//        Camera camera = new Camera(cameraPosition);

        // Construct a light, use a constructor that sets it at point p
//        Light[] lights = { new Light(new Vector3(0, 4, -4)) };

//        render(scene, camera, lights, 4, new File("renders/seahorse.png"));

        Scene scene = new Scene("renders/seahorse.obj");
        PhongRender phongRender = new PhongRender(scene2);
        phongRender.render();

        JFrame frame = new JFrame() {
            @Override
            public void paint(Graphics g) {
                Raster raster = scene.getRaster();
                BufferedImage image = raster.create();
                g.drawImage(image, 0, 0, null);
                try {
                    raster.writeImage(image);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        frame.setBounds(0, 0, scene.getRaster().getWidth(), scene.getRaster().getHeight());
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    public void render() {
        long startTime = System.currentTimeMillis();
        List<Pixel> pixels = new ArrayList<>();
        int width = raster.getWidth();
        int height = raster.getHeight();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixels.add(new Pixel(x, y));
            }
        }
        drawPixel(scene, pixels);

        long totalTime = (System.currentTimeMillis() - startTime);
        System.out.print("Executed in " + (totalTime / 1000.0) + " seconds.");
    }

    public Color getRgbPixel(Object3d[] scene, Ray ray) {
        Intersection intersectInfo = ObjLoader.vertices().getIntersection(ray);
        if (intersectInfo == null) {
            return new Color();
        }

        return scene.getPhong().getPointColor(scene, intersectInfo);
    }

    //tracing
    public void drawPixel(Object3d[] scene, List<Pixel> pixels) {
        int width = raster.getWidth();
        int height = raster.getHeight();

        for (Pixel pixel : pixels) {
            Ray ray = observer.getRay((pixel.getX()) / width, (pixel.getY()) / height);
            Color rayColor = getRgbPixel(scene, ray);
            pixel.setColor(rayColor);
        }
        for (Pixel pixel : pixels) {
            raster.setPixelColor(pixel.getColor(), (int) pixel.getX(), (int) pixel.getY());
        }
    }
}
