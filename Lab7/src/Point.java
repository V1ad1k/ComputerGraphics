

import org.json.JSONObject;

public class Point {

    public double x;
    public double y;
    public double z;

    public Point() {
    }

    public Point(Point other) {
        this.x = other.x;
        this.y = other.y;
        this.z = other.z;
    }

    public Point(JSONObject object) {
        this.x = object.getDouble("x");
        this.y = object.getDouble("y");
        this.z = object.getDouble("z");
    }

    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void add(Point other) {
        this.x += other.x;
        this.y += other.y;
        this.z += other.z;
    }

}
