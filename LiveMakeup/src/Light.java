import java.util.List;

public class Light {
    private Point pos;
    private Color intens;

    public Light(List<String> light) {
        pos = new Point(Double.parseDouble(light.get(1)),Double.parseDouble(light.get(2)),Double.parseDouble(light.get(3)));
        intens = new Color(Double.parseDouble(light.get(4)),Double.parseDouble(light.get(5)),Double.parseDouble(light.get(6)));
    }

    public Point getPosition() { return pos; }
    public Color getIntensity() {
        return intens;
    }

}