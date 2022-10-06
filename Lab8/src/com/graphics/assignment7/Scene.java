package com.graphics.assignment7;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;


public class Scene {

    private Observer observer;

    private List<Light> lights = new ArrayList<Light>();

    public List<Light> getLights() {
        return this.lights;
    }

    private List<Sphere> spheres = new ArrayList<>();

    private Raster raster;

    private Phong phong;

    public Scene(JSONObject scene) {
        observer = new Observer(scene.getInt("distance"));
        JSONArray spheresArray = scene.getJSONArray("spheres");
        for (int i = 0; i< spheresArray.length(); i++) {
            Sphere sphere = new Sphere(spheresArray.getJSONObject(i));
            spheres.add(sphere);
        }
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


    public List<Sphere> getSpheres() {
        return spheres;
    }

    public Observer getObserver() {
        return observer;
    }

    public Phong getPhong() {
        return phong;
    }

}
