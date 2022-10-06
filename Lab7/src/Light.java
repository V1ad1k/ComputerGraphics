
import org.json.JSONObject;


public class Light {

    private Point position;
    private Color intensity;

    public Light(JSONObject light) {
        position = new Point(light.getJSONObject("pos"));
        intensity = new Color(light.getJSONObject("int"));
    }

    public Point getPosition() {
        return position;
    }

    public Color getIntensity() {
        return intensity;
    }
}