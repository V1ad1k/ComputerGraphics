import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Scene {

    private Observer observer;

    private List<Light> lights = new ArrayList<Light>();

    public List<Light> getLights() {
        return this.lights;
    }

    private Sphere sphere;

    private Raster raster;

    private Phong phong;

    public Scene(JSONObject scene) {
        observer = new Observer(scene.getInt("distance"));
        sphere = new Sphere(scene.getJSONObject("sphere"));
        phong = new Phong(scene.getJSONObject("phong"));
        raster = new Raster(scene.getInt("width"), scene.getInt("height"));
        JSONArray lightsArray = scene.getJSONArray("lights");
        for (int i = 0; i < lightsArray.length(); i++) {
            JSONObject light = lightsArray.getJSONObject(i);
            lights.add(new Light(light));
        }
    }

    public Raster getRaster() {
        return this.raster;
    }


    public Sphere getSphere() {
        return sphere;
    }

    public Observer getObserver() {
        return observer;
    }

    public Phong getPhong() {
        return phong;
    }

}
