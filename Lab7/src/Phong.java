
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

    }


    public Color getPointColor(Scene scene, Intersection intersection) {


        //observer vector
        Vector o = new Vector(scene.getObserver().getViewPoint());
        o.subtract(intersection.getLocation());
        o.normalize();

        Color result = new Color();
        for (Light light : scene.getLights()) {

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

            //todo: implement fading from script
        }

        //add ambient
        result.addR(a.getR() * kac);
        result.addG(a.getG() * kac);
        result.addB(a.getB() * kac);
        return result;
    }


}