

public class Observer {

    private Point viewPoint;

    private Vector unitX = new Vector(1, 0, 0);
    private Vector unitY = new Vector(0, 1, 0);
    private Vector unitZ = new Vector(0, 0, -1);

    public Observer(int distance) {
        viewPoint = new Point(0, 0, distance);
    }

    public Ray getRay(double x, double y) {
        Ray result = new Ray();
        result.setViewPoint(new Point(viewPoint));
        result.setDirection(new Vector(unitZ));
        result.getDirection().add(Vector.multiplyByNumber(unitX, x - 0.5));
        result.getDirection().add(Vector.multiplyByNumber(unitY, y - 0.5));
        return result;
    }

    public Point getViewPoint() {
        return viewPoint;

    }
}
