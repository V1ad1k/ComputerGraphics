
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Random;


public class Draw extends JFrame {

    public static void main(String[] args) {
        Draw makePaint = new Draw();
        makePaint.setVisible(true);
    }

    private static final File IMAGES_DIRECTORY = new File("./img");
    private static final File PAINT_DIRECTORY = new File("./paint");

    private Paint paint = new Paint();

    public Draw() {
        setBounds(400, 200, 1000, 750);
        setTitle("Lab3 by Vlad");
        setBackground(Color.white);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel additionalPanel = new JPanel();
        additionalPanel.setLayout(new GridLayout(1, 1));

        ShapeSelection imageSelection = new ShapeSelection(IMAGES_DIRECTORY, false);
        additionalPanel.add(imageSelection.getComponent());


        add(additionalPanel, BorderLayout.WEST);

        Triangle triangle = new Triangle(300, 300, 0,0,0);

        int[] point1 = { 90, 42 };
        int[] point2 = { 105, 123 };
        int[] point3 = { 175, 76 };
        Random ran = new Random();
        int x = ran.nextInt(255);
        int y = ran.nextInt(255);

        int[] color1random = { 255, x ,y};
        int[] color2random = { x, 255, y };
        int[] color3random = { x, y, 255 };

        triangle.draw_triangle(point1, point2, point3, color1random, color2random,  color3random);
        triangle.convert2background();
        triangle.write("img/triangle1.jpg");
    }

}
