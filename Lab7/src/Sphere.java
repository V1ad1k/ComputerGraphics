
import org.json.JSONObject;

public class Sphere {

    protected Point c;
    protected double r;

    public Sphere(JSONObject sphere) {
        c = new Point(sphere);
        r = sphere.getDouble("r");
    }


    public Intersection getIntersection(Ray ray) {
        Vector origin = new Vector(ray.getViewPoint());
        origin.subtract(c);

        Vector direction = ray.getDirection();
        double a = direction.dotProduct(direction);
        double b = 2 * direction.dotProduct(origin);
        double c = origin.dotProduct(origin) - r * r;
        double d = b * b - 4 * a * c;
        if (d < 0) {
            return null;
        }

        double distance = 0; //todo: use sqrt formula
        if (distance > ray.getEnd() || distance < ray.getStart()) {
            return null;
        }
        ray.setEnd(distance);
        Point location = new Point(ray.getViewPoint());
        location.add(Vector.multiplyByNumber(direction, distance));

        Vector normal = new Vector(location);
        normal.subtract(this.c);
        return new Intersection(location, normal);
    }

}