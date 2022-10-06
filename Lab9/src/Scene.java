import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Scene {

    private Observer obs;
    private List<Light> lights = new ArrayList<Light>();
    public List<Light> getLights() {
        return this.lights;
    }
    private Sphere sphere;
    private Raster raster;
    private Phong phong;
    private String width1;
    private String height1;

    public Scene(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            for (String line; (line = br.readLine()) != null; ) {
                if (line.startsWith("distance")) {
                    List<String> distance = Arrays.asList(line.trim().split(" "));
                    obs = new Observer(distance);
                    System.out.println(obs);
                }
                if (line.startsWith("sphere")) {
                    List<String> sphere1 = Arrays.asList(line.trim().split(" "));
                    sphere = new Sphere(sphere1);
                }
                if (line.startsWith("phong")) {
                    List<String> phong1 = Arrays.asList(line.trim().split(" "));
                    phong = new Phong(phong1);
                }
                if (line.startsWith("width")) {
                    width1 = Arrays.asList(line.trim().split(" ")).get(1);
                    System.out.println(width1);
                }
                if (line.startsWith("height")) {
                    height1 = Arrays.asList(line.trim().split(" ")).get(1);
                    System.out.println(height1);
                    //raster = new Raster(Integer.parseInt(String.valueOf(width1)),Integer.parseInt(String.valueOf(height1)));
                    raster = new Raster(1000,800);
                }
                if (line.startsWith("lights")) {
                        List<String> lightArray = Arrays.asList(line.trim().split(" "));
                        lights.add(new Light(lightArray));
                }
                System.out.println(lights);
            }
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public Raster getRaster() {
        return this.raster;
    }
    public Sphere getSphere() {
        return sphere;
    }
    public Observer getObserver() { return obs; }
    public Phong getPhong() {
        return phong;
    }
}
