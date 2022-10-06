package com.graphics.assignment7;

import org.json.JSONObject;


public class Phong {

    //diffuse
    private double kdr;
    private double kdg;
    private double kdb;

    //specular
    private double ksr;
    private double ksg;
    private double ksb;

    //gloss
    private final double g;

    //ambient
    protected final Color a;
    private final double kac;

    //fading
    private final double c0;
    private final double c1;
    private final double c2;


    public Phong(JSONObject phong) {
        //diffuse
        this.kdr = phong.getDouble("kdr");
        this.kdg = phong.getDouble("kdg");
        this.kdb = phong.getDouble("kdb");

        //specular
        this.ksr = phong.getDouble("ksr");
        this.ksg = phong.getDouble("ksg");
        this.ksb = phong.getDouble("ksb");

        //gloss
        g = phong.getDouble("g");

        //ambient
        JSONObject ambient = phong.getJSONObject("ambient");
        a = new Color(ambient);
        kac = ambient.getDouble("kac");

        //fading
        JSONObject fading = phong.getJSONObject("fading");
        c0 = fading.getDouble("c0");
        c1 = fading.getDouble("c1");
        c2 = fading.getDouble("c2");
    }


    private double fading(double d) {
        double y = c2 * d * d + c1 * d + c0;
        if (y == 0) return 1;
        return Math.min(1 / y, 1);
    }


    public Color getPointColor(Scene scene, Intersection intersection) {

        //N - unit surface normal vector
        Vector n = intersection.getNormal();

        //observer vector
        Vector o = new Vector(scene.getObserver().getViewPoint());
        o.subtract(intersection.getLocation());
        o.normalize();
        Color result = new Color();
        for (Light light : scene.getLights()) {
            Vector phiVector = new Vector(intersection.getLocation());
            phiVector.subtract(light.getPosition());
            phiVector.normalize();
            //I - unit vector to i-th light
            Vector i = new Vector(light.getPosition());
            i.subtract(intersection.getLocation());
            //distance from light position to sphere point.
            double r = i.length();
            i.normalize();

            //(OS)
            Vector os = new Vector(i);
            os.add(o);
            os.normalize();

            double dotni = Math.max(0, n.dot(i));
            double dotos = Math.pow(Math.max(0, n.dot(os)), g);

            Color li = light.getIntensity();

            double fading = fading(r);

            result.addR(fading * li.getR() * (kdr * dotni + ksr * dotos));
            result.addG(fading * li.getG() * (kdg * dotni + ksg * dotos));
            result.addB(fading * li.getB() * (kdb * dotni + ksb * dotos));
        }

        //add ambient
        result.addR(a.getR() * kac);
        result.addG(a.getG() * kac);
        result.addB(a.getB() * kac);
        return result;
    }


}