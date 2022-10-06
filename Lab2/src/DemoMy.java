//import java.awt.BasicStroke;
//import java.awt.Color;
//import java.awt.Container;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.util.Scanner;
//import javax.swing.*;
//import java.awt.*;
//import java.lang.Math;
//import java.util.ArrayList;
//
//import java.awt.event.*;
//import java.awt.geom.Line2D;
//import java.awt.geom.Rectangle2D;
//
//
//
///**
// * @param args
// */
//public static void main(String[] args)
//        {
//        Scanner in = new Scanner(System.in);
//
//        SmpWindow wnd = new SmpWindow();
//        wnd.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
//        wnd.setVisible(true);
//        wnd.setBounds( 70, 70, 450, 300);
//        wnd.setTitle( "Simple vector graphics program, (C) J.Sas, 2008" );
//        }
//
//
//class DrawWndPane  extends JPanel implements ActionListener, MouseListener, MouseMotionListener
//{
//    JButton button;
//
//    // Interaction state variables:
//    // =======================================
//    // Index of line being dragged or -1 if none
//    int line_caught;
//    // Index of line end point caught: 0 or 1 or 2 for center
//    int point_caught;
//
//    ArrayList<Line2D.Double> l_list;
//
//
//    DrawWndPane() {
//        super();
//
//        // llist = new ArrayList<Line2D.Double>();
//
//        setLayout(null);
//
//        setBackground(Color.white);
//
//        button = new JButton("Reset");
//        button.setBounds(10, 10, 70, 30);
//        add(button);
//
//        l_list = new ArrayList<Line2D.Double>();
//
//        l_list.add(new Line2D.Double());
//        l_list.add(new Line2D.Double());
//
//        initLines();
//
//        button.addActionListener(this);
//        addMouseListener(this);
//        addMouseMotionListener(this);
//
//    }
//
//    private void initLines() {
//
//        // Remove dynamicly added lines
//        while (l_list.size() > 2)
//            l_list.remove(2);
//
//        Line2D.Double line;
//
//        line = l_list.get(0);
//        line.x1 = 10;
//        line.y1 = 100;
//        line.x2 = 100;
//        line.y2 = 100;
//
//        line = l_list.get(1);
//        line.x1 = 10;
//        line.y1 = 130;
//        line.x2 = 100;
//        line.y2 = 130;
//
//        line_caught = -1;
//    }
//
//    class SmpWindow extends JFrame {
//        public SmpWindow() {
//            // Acquire drawing surface and add own panel to it
//            // Pozyskanie dostepu do powierzchni rysowania okna
//            Container contents = getContentPane();
//            contents.add(new DrawWndPane());
//        }
//
//    }
//}