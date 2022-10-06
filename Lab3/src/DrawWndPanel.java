//import javax.imageio.ImageIO;
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//import java.awt.event.MouseMotionListener;
//import java.awt.image.BufferedImage;
//import java.awt.image.ImageObserver;
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class DrawWndPanel extends JPanel {
//
//    private List<DrawnShape> drawings;
//    private DrawnShape curShape;
//    public int used_mode = 0;
//    BufferedImage input_image = null;
//    int img_width;
//    int img_height;
//
//    DrawWndPanel() {
//        drawings = new ArrayList<DrawnShape>();
//        this.setBackground(new Color(200, 200, 200));
//        addMouseListener(clickListener);
//        addMouseMotionListener(moveListener);
//    }
//
//    public void LoadImage(String iname) {
//        try {
//            this.input_image = ImageIO.read(new File(iname));
//        } catch (IOException var3) {
//            this.input_image = null;
//            System.out.println("Image " + iname + " load failed");
//            return;
//        }
//        System.out.println("Image downloaded.");
//        this.img_height = this.input_image.getHeight();
//        this.img_width = this.input_image.getWidth();
//    }
//
//    public void SetMode(int mode) {
//        this.used_mode = mode;
//        System.out.println("Mode set to " + mode);
//        this.repaint();
//    }
//
//    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        Graphics2D g2d = (Graphics2D) g;
//        Rectangle b;
//        b = this.getBounds();
//        double s = (double) b.height / (double) this.img_height;
//        g2d.drawLine(SmpWindow.getWidth1() / 2, SmpWindow.getHeight1(), SmpWindow.getWidth1() / 2, 0);
//        g2d.drawLine(0, SmpWindow.getHeight1() / 2, SmpWindow.getWidth1() / 2, SmpWindow.getHeight1() / 2);
//        g2d.drawRect(SmpWindow.getWidth1() / 6, SmpWindow.getHeight1() - SmpWindow.getHeight1() / 6, 70, 70);
//        g2d.drawOval(SmpWindow.getWidth1() / 6, SmpWindow.getHeight1() - SmpWindow.getHeight1() / 3, 70, 70);
//        //g2d.drawImage(this.input_image, 60, 60, (ImageObserver)null);
//        g2d.drawImage(this.input_image, 100, 100, (int) (s * (double) this.img_width / 8), b.height / 8, (ImageObserver) null);
//        for (DrawnShape a : drawings) {
//            a.draw(g2d);
//        }
//        g2d.setColor(Color.BLACK);
//        g2d.setStroke(new BasicStroke(2));
//
//        if (curShape == null)
//            return;
//        curShape.draw(g2d);
//    }
//
//    private MouseMotionListener moveListener = new MouseMotionListener() {
//        @Override
//        public void mouseDragged(MouseEvent e) {
//            curShape = new DrawnShape(curShape.getClickP(), e.getPoint());
//            repaint();
//        }
//
//        @Override
//        public void mouseMoved(MouseEvent e) {
//
//        }
//    };
//
//    private MouseListener clickListener = new MouseListener() {
//        @Override
//        public void mouseClicked(MouseEvent e) {
//            curShape = new DrawnShape(e.getPoint(), e.getPoint());
//            repaint();
//        }
//
//        @Override
//        public void mousePressed(MouseEvent e) {
//            curShape = new DrawnShape(e.getPoint(), e.getPoint());
//        }
//
//        @Override
//        public void mouseReleased(MouseEvent e) {
//            drawings.add(new DrawnShape(curShape.getClickP(), e.getPoint()));
//            curShape = null;
//        }
//
//        @Override
//        public void mouseEntered(MouseEvent e) {
//
//        }
//
//        @Override
//        public void mouseExited(MouseEvent e) {
//
//        }
//    };
//
//    class DrawnShape {
//
//        private Point p1, p2;
//
//        public DrawnShape(Point p1, Point p2) {
//            this.p1 = p1;
//            this.p2 = p2;
//        }
//
//        public Point getClickP() {
//            return p1;
//        }
//
//        public void draw(Graphics2D g) {
//            g.drawLine(p1.x, p1.y, p2.x, p1.y);
//            g.drawLine(p1.x, p1.y, p1.x, p2.y);
//            g.drawLine(p2.x, p2.y, p2.x, p1.y);
//            g.drawLine(p2.x, p2.y, p1.x, p2.y);
//        }
//    }
//}