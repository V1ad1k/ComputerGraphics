import java.util.List;

public class Color {

    private double r;
    private double g;
    private double b;

    public Color() {
    }

    public Color(List<Double> color) {
        this.r = color.get(0);
        this.g = color.get(1);
        this.b = color.get(2);
    }

    public Color(double r, double g, double b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    private static int controlColor(double color) {
        if (color < 0) { return 0; }
        if (color > 255) { return 255; }
        return (int) color;
    }

    public int toRgb() {
        return (controlColor(r) << 16) | (controlColor(g) << 8) | (controlColor(b));
    }
    public double getR() {
        return r;
    }
    public void addR(double r) { this.r += r; }
    public double getG() {
        return g;
    }
    public void addG(double g) { this.g += g; }
    public double getB() {
        return b;
    }
    public void addB(double b) {
        this.b += b;
    }
}