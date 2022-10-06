import java.util.ArrayList;
import java.util.List;


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

    //attenuation
    private final double c0;
    private final double c1;
    private final double c2;


    public Phong(List<String> phong) {
        this.kdr = Double.parseDouble(phong.get(1));
        this.kdg = Double.parseDouble(phong.get(2));
        this.kdb = Double.parseDouble(phong.get(3));
        this.ksr = Double.parseDouble(phong.get(4));
        this.ksg = Double.parseDouble(phong.get(5));
        this.ksb = Double.parseDouble(phong.get(6));
        g = Double.parseDouble(phong.get(7));
        List<Double> amb = new ArrayList<>();
        double ambient1 = Double.parseDouble(phong.get(8));
        double ambient2 = Double.parseDouble(phong.get(9));
        double ambient3 = Double.parseDouble(phong.get(10));
        amb.add(ambient1);
        amb.add(ambient2);
        amb.add(ambient3);
        a = new Color(amb);
        kac = Double.parseDouble(phong.get(11));
        c0 = Double.parseDouble(phong.get(12));
        c1 = Double.parseDouble(phong.get(13));
        c2 = Double.parseDouble(phong.get(14));
    }


    private double attenuation(double d) {
        double y = c2 * d * d + c1 * d + c0;
        if (y == 0) return 1;
        return Math.min(1 / y, 1);
    }


    public Color getPointColor(Scene scene, Intersection intersection) {

        //unit surface normal vector
        Vector n = intersection.getNormal();

        //observer vector
        Vector o = new Vector(scene.getObserver().getViewPoint());
        o.subtract(intersection.getLocation());
        o.normalize();

        Color result = new Color();
        for (Light light : scene.getLights()) {

            //unit vector to i-th light
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

            double attenuation = attenuation(r);
            result.addR(attenuation * li.getR() * (kdr * dotni + ksr * dotos));
            result.addG(attenuation * li.getG() * (kdg * dotni + ksg * dotos));
            result.addB(attenuation * li.getB() * (kdb * dotni + ksb * dotos));

        }

        //append ambient
        result.addR(a.getR() * kac);
        result.addG(a.getG() * kac);
        result.addB(a.getB() * kac);
        return result;
    }

}