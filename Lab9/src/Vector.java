public class Vector extends Point {

    public Vector(Point other) {
        super(other);
    }

    public Vector() {

    }

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double dot(Vector other) { return x * other.x + y * other.y + z * other.z; }

    public void normalize() {
        double len = length();
        if (len == 0) { return; }
        this.x /= len;
        this.y /= len;
        this.z /= len;
    }

    public double length() { return Math.sqrt(x * x + y * y + z * z); }

    public void subtract(Point p2) {
        this.x -= p2.x;
        this.y -= p2.y;
        this.z -= p2.z;
    }

    public static Vector scale(Vector other, double scale) {
        Vector result = new Vector(other);
        result.x *= scale;
        result.y *= scale;
        result.z *= scale;
        return result;
    }
}