import java.math.BigDecimal;
import java.math.RoundingMode;

public class RGB2HSV
{
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    static void rgb_to_hsv(double r, double g, double b)
    {

        // R, G, B values are divided by 255
        // to change the range from 0..255 to 0..1
        r = r / 255.0;
        g = g / 255.0;
        b = b / 255.0;

        // h, s, v = hue, saturation, value
        double cmax = Math.max(r, Math.max(g, b)); // maximum of r, g, b
        double cmin = Math.min(r, Math.min(g, b)); // minimum of r, g, b
        double diff = cmax - cmin; // diff of cmax and cmin.
        double h = -1, s = -1;

        // if cmax and cmax are equal then h = 0
        if (cmax == cmin)
            h = 0;

            // if cmax equal r then compute h
        else if (cmax == r)
            h = (60 * ((g - b) / diff) + 360) % 360;

            // if cmax equal g then compute h
        else if (cmax == g)
            h = (60 * ((b - r) / diff) + 120) % 360;

            // if cmax equal b then compute h
        else if (cmax == b)
            h = (60 * ((r - g) / diff) + 240) % 360;

        // if cmax equal zero
        if (cmax == 0)
            s = 0;
        else
            s = (diff / cmax) * 100;

        // compute v
        double v = cmax * 100;
        System.out.println("(" + h + "\t\t" + round(s,6) + "\t\t\t" + v + ")");

    }

    // Driver Code
    public static void main(String[] args)
    {
        rgb_to_hsv(2, 250, 0);
        rgb_to_hsv(250, 249, 248);
        rgb_to_hsv(230, 230, 230);
        rgb_to_hsv(0, 255, 0);
        rgb_to_hsv(1, 20, 1);
        rgb_to_hsv(30, 80, 20);

    }
}