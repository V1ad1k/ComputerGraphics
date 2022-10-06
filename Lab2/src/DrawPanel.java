import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.*;

public class DrawPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private ArrayList<Point> points;
    private ArrayList<Ellipse2D.Double> points2;
    public DrawPanel() {
        JButton button;


        button = new JButton("Reset");
        button.setBounds(10, 10, 70, 30);
        add(button);
        points2 = new ArrayList<>();
        points = new ArrayList<Point>();
        setBackground(Color.WHITE);
        int counter = 1;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                points.add(new Point(e.getX(), e.getY()));
                points2.add(new Ellipse2D.Double());
                repaint();
            }
        });
    }

    private void initLines()
    {

    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.red);
        for (Point point : points) {
            g2.fillOval(point.x, point.y, 20, 20);

        }
        for (Ellipse2D.Double p : points2) g2.fillOval((int)(p.getX()), (int)(p.getY()), 40, 40);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame();
                frame.add(new DrawPanel());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(400, 400);
                frame.setVisible(true);
            }
        });
    }
}