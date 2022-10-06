
import java.util.List;

public class Sphere {

    private final Point center;
    private final double radius;

    public Sphere(List<String> sphere) {
        center = new Point(sphere);
        double x,y,z;
        x =  Double.parseDouble(sphere.get(1));
        y =  Double.parseDouble(sphere.get(2));
        z =  Double.parseDouble(sphere.get(3));
        radius = Double.parseDouble(sphere.get(4));
    }


    public Intersection getIntersection(Ray ray) {
        Vector origin = new Vector(ray.getViewPoint());
        origin.subtract(center);

        Vector direction = ray.getDirection();         //ray from sphere to obs
        double a = direction.dot(direction);   // to get magnitude of ray
        double b = 2 * direction.dot(origin);
        double c = origin.dot(origin) - radius * radius;
        double d = b * b - 4 * a * c;
        if (d < 0) { return null; }

        double distance = (d == 0 ? -b : -b - Math.sqrt(d)) / (2 * a);
        if (distance > ray.getEnd() || distance < ray.getStart()) {
            return null;
        }
        ray.setEnd(distance);
        Point location = new Point(ray.getViewPoint());
        location.add(Vector.scale(direction, distance));

        Vector normal = new Vector(location);        //create normal vector
        normal.subtract(this.center);
        normal.normalize();
        return new Intersection(location, normal);
    }
}