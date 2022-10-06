import java.util.List;

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

    public Point(List<String> object) {
        this.x = Double.parseDouble(object.get(1));
        this.y = Double.parseDouble(object.get(2));
        this.z = Double.parseDouble(object.get(3));
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
