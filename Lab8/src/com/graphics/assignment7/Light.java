package com.graphics.assignment7;

import org.json.JSONObject;


public class Light {

    private Point position;
    private Color intensity;
    private Vector direction;

    public Light(JSONObject light) {
        position = new Point(light.getJSONObject("pos"));
        intensity = new Color(light.getJSONObject("int"));
        direction = new Vector(light.getJSONObject("dir"));
        direction.normalize();
    }

    public Point getPosition() {
        return position;
    }

    public Color getIntensity() {
        return intensity;
    }

    public Vector getDirection() {
        return direction;
    }

}