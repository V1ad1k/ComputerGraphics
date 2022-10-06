import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import javax.swing.*;

@SuppressWarnings("serial")
public class DrawTriangle extends JPanel {
    private static final int PREF_W = 600;
    private static final int PREF_H = PREF_W;
    private static final Color COLOR_1 = Color.blue;
    private static final Color COLOR_2 = Color.red;
    private static final Paint GRADIENT_PAINT = new GradientPaint(0, 0, COLOR_1,
            20, 20, COLOR_2, true);
    private Path2D myPath = new Path2D.Double();

    public DrawTriangle() {
        double firstX = (PREF_W / 2.0) * (1 - 1 / Math.sqrt(3));
        double firstY = 3.0 * PREF_H / 4.0;

        myPath.moveTo(firstX, firstY);
        myPath.lineTo(PREF_W - firstX, firstY);
        myPath.lineTo(PREF_W / 2.0, PREF_H / 4.0);
        myPath.closePath();

        MyMouseAdapter myMouseAdapter = new MyMouseAdapter();
        addMouseListener(myMouseAdapter);
        addMouseMotionListener(myMouseAdapter);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setPaint(GRADIENT_PAINT);
        g2.fill(myPath);
    }

    @Override
    public Dimension getPreferredSize() {
        if (isPreferredSizeSet()) {
            return super.getPreferredSize();
        }
        return new Dimension(PREF_W, PREF_H);
    }

    private class MyMouseAdapter extends MouseAdapter {
        private Point pPressed = null;

        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getButton() != MouseEvent.BUTTON1) {
                return;
            }
            if (myPath.contains(e.getPoint())) {
                pPressed = e.getPoint();
            }
        }

        public void mouseDragged(MouseEvent e) {
            drag(e);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            drag(e);
            pPressed = null;
        }

        private void drag(MouseEvent e) {
            if (pPressed == null) {
                return;
            }
            Point p = e.getPoint();
            int tx = p.x - pPressed.x;
            int ty = p.y - pPressed.y;
            AffineTransform at = AffineTransform.getTranslateInstance(tx, ty);
            myPath.transform(at);
            pPressed = p;
            repaint();
        }

        ;
    }
        private static void createAndShowGui() {
            DrawTriangle mainPanel = new DrawTriangle();

            JFrame frame = new JFrame("Path2DExample");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.getContentPane().add(mainPanel);
            frame.pack();
            frame.setLocationByPlatform(true);
            frame.setVisible(true);
        }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    createAndShowGui();
                }
            });
        }
    }