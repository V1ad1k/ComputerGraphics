package com.graphics.assignment7;

import org.json.JSONObject;

public class Sphere {

    private final Point center;
    private final double radius;

    public Sphere(JSONObject sphere) {
        center = new Point(sphere);
        radius = sphere.getDouble("r");
    }


    public Intersection getIntersection(Ray ray) {
        Vector origin = new Vector(ray.getViewPoint());
        origin.subtract(center);

        //ray from the sphere to observer
        Vector direction = ray.getDirection();
        //finding real roots of the quadratic equation to get ray magnitude
        double a = direction.dot(direction);
        double b = 2 * direction.dot(origin);
        double c = origin.dot(origin) - radius * radius;
        double d = b * b - 4 * a * c;
        if (d < 0) {
            return null;
        }

        double distance = (d == 0 ? -b : -b - Math.sqrt(d)) / (2 * a);
        if (distance > ray.getEnd() || distance < ray.getStart()) {
            return null;
        }
        ray.setEnd(distance);
        Point location = new Point(ray.getViewPoint());
        location.add(Vector.scale(direction, distance));

        Vector normal = new Vector(location);
        normal.subtract(this.center);
        normal.normalize();
        return new Intersection(location, normal);
    }

}