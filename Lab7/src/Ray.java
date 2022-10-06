
public class Ray {

    private Point viewPoint = new Point();
    private Vector direction = new Vector();

    private double start = Double.NEGATIVE_INFINITY;
    private double end = Double.POSITIVE_INFINITY;

    public Ray() {
    }

    public Point getViewPoint() {
        return viewPoint;
    }

    public void setViewPoint(Point viewPoint) {
        this.viewPoint = viewPoint;
    }

    public Vector getDirection() {
        return direction;
    }

    public void setDirection(Vector direction) {
        this.direction = direction;
    }

    public double getStart() {
        return start;
    }

    public double getEnd() {
        return end;
    }

    public void setEnd(double end) {
        this.end = end;
    }
}
