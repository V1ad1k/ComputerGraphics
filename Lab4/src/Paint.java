
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Random;
import static java.lang.Math.*;

public class Paint {

    public static void main(String[] args) {
        //args = 55
        JFrame frame = new JFrame();
        frame.setSize(750, 500);
        final RainPanel rainPanel = new RainPanel();
        rainPanel.calculateChance(args[0]);
        frame.add(rainPanel);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                rainPanel.stop();
                System.exit(0);
            }
        });
    }
}

class RainPanel extends JPanel {
    private float gravity = 3.3f;
    private double rainChance;

    private int Period = 15;
    private Color rainColor = new Color(0, 100, 255);


    private ArrayList<TriangleN> plot;
    private UpdateThread updateThread;

    public RainPanel() {
        plot = new ArrayList<>();
        updateThread = new UpdateThread();
        updateThread.start();
    }

    public void calculateChance(String arg0) {
        double chance = Double.parseDouble(arg0);
        rainChance = chance / random();
    }

    public void stop() {
        updateThread.stopped = true;
    }

    public int getHeight() {
        return this.getSize().height;
    }

    public int getWidth() {
        return this.getSize().width;
    }

    private class UpdateThread extends Thread {
        public volatile boolean stopped = false;

        @Override
        public void run() {
            while (!stopped) {
                RainPanel.this.repaint();
                try {
                    Thread.sleep(Period);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(rainColor);

        for (TriangleN rain : plot) {
            rain.update();
            rain.draw(g2);
        }

        if (random()/2 < rainChance) {
            plot.add(new TriangleN());
        }
    }

    class TriangleN {
        float x;
        float y;
        double width = 12.5;
        double height = width * 2.5;

        public TriangleN() {
            Random r = new Random();
            x = r.nextInt(getWidth());
            y = 0;
        }

        public void update() {
            y += gravity;
        }

        public void draw(Graphics2D g2) {
            Shape raining = new Ellipse2D.Double(x, y, width, height);
            Shape triangle = (Shape) new Triangle((int)width, (int)height, 255,0,0);
            g2.draw(triangle);
            //g2.fill(triangle);
        }

    }
}
