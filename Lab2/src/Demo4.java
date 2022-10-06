///*
// * Computer graphics courses at Wroclaw University of Technology
// * (C) by Jurek Sas, 2008
// *
// * Description:
// *   This demo explains how to use mouse related events in simple user
// *   interaction in graphical program
// *
// *   Ten program demonstracyjny pokazuje jak zastosowaæ zdarzenia myszy
// *   do interakcji z u¿ytkownikiem w prostym programie graficznym
// */
//
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
//public class Demo4 {
//
//    /**
//     * @param args
//     */
//    public static void main(String[] args)
//    {
//        Scanner in = new Scanner(System.in);
//
//        SmpWindow wnd = new SmpWindow();
//        wnd.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
//        wnd.setVisible(true);
//        wnd.setBounds( 70, 70, 450, 300);
//        wnd.setTitle( "Simple vector graphics program, (C) J.Sas, 2008" );
//    }
//
//}
//
//class DrawWndPane  extends JPanel implements ActionListener, MouseListener, MouseMotionListener
//{
//    JButton button;
//    private ArrayList<Point> points;
//
//    // Interaction state variables:
//    // =======================================
//    // Index of line being dragged or -1 if none
//    int  line_caught;
//    // Index of line end point caught: 0 or 1 or 2 for center
//    int  point_caught;
//
//    ArrayList<Line2D.Double> l_list;
//
//
//    DrawWndPane()
//    {
//        super();
//        // llist = new ArrayList<Line2D.Double>();
//
//        setLayout( null );
//
//        setBackground( Color.white );
//
//        button = new JButton( "Reset" );
//        button.setBounds( 10, 10, 70, 30 );
//        add( button );
//
//        l_list = new ArrayList<Line2D.Double>();
//        points = new ArrayList<Point>();
//        l_list.add( new Line2D.Double());
//        l_list.add( new Line2D.Double());
//
//        initLines();
//
//        button.addActionListener( this );
//        addMouseListener( this );
//        addMouseMotionListener( this );
//
//    }
//    private void initLines()
//    {
//
//        // Remove dynamicly added lines
//        while ( l_list.size() > 2 )
//            l_list.remove( 2 );
//
//        Line2D.Double  line;
//
//        line = l_list.get( 0 );
//        line.x1 = 10;
//        line.y1 = 100;
//        line.x2 = 100;
//        line.y2 = 100;
//
//        line = l_list.get( 1 );
//        line.x1 = 10;
//        line.y1 = 130;
//        line.x2 = 100;
//        line.y2 = 130;
//
//        line_caught = -1;
//    }
//
//    private boolean CatchClosePoint( double  x, double y )
//    {
//        for ( int i = 0; i < l_list.size(); i++ )
//        {
//            Line2D.Double  line;
//            line = l_list.get( i );
//
//            if ( (Math.abs( line.x1 - x) < 10) && (Math.abs( line.y1 - y) < 10) )
//            {
//                line_caught = i;
//                point_caught = 0;
//                return true;
//            }
//            if ( (Math.abs( line.x2 - x) < 10) && (Math.abs( line.y2 - y) < 10) )
//            {
//                line_caught = i;
//                point_caught = 1;
//                return true;
//            }
//            double xc = 0.5 * (line.x1 + line.x2);
//            double yc = 0.5 * (line.y1 + line.y2);
//            if ( (Math.abs( xc - x) < 10) && (Math.abs( yc - y) < 10) )
//            {
//                line_caught = i;
//                point_caught = 2;
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private void initDisplay()
//    {
//        Graphics g = getGraphics();
//        super.paintComponent(g);
//        Graphics2D  g2d = (Graphics2D)g;
//        g2d.setStroke( new BasicStroke( 1 ));
//        g2d.setXORMode( Color.white );
//
//        // Delete at previous position
//        for( int i = 0; i < l_list.size(); i++)
//            g2d.draw( l_list.get( i ) );
//
//    }
//
//    private void UpdateLinePosition( int x, int y )
//    {
//        if ( line_caught >= 0 )
//        {
//            Graphics g = getGraphics();
//            Graphics2D  g2d = (Graphics2D)g;
//
//            //g2d.setXORMode( getBackground() );
//            g2d.setXORMode( new Color( 255,255,255) );
//            g2d.setXORMode( Color.red );
//
//            Line2D.Double  line;
//            line = l_list.get( line_caught );
//
//            // Delete at previous position
//            g2d.draw( line );
//
//            // Update line end position
//            if ( point_caught == 0 )
//            {
//                line.x1 = x;
//                line.y1 = y;
//            }
//            else
//            if ( point_caught == 1 )
//            {
//                line.x2 = x;
//                line.y2 = y;
//            }
//            else
//            if ( point_caught == 2 )
//            {
//                double xc = x - 0.5 * (line.x1 + line.x2);
//                double yc = y - 0.5 * (line.y1 + line.y2);
//                line.x1 += xc;
//                line.y1 += yc;
//                line.x2 += xc;
//                line.y2 += yc;
//            }
//
//            // Draw line at new position
//            g2d.draw( line );
//            g2d.setPaintMode();
//        }
//    }
//    public void paintComponent( Graphics g)
//    {
//        g.setColor( Color.white );
//        super.paintComponent(g);
//
//        Graphics2D  g2d = (Graphics2D)g;
//
//        // Fill rectangular area to show how XOR drawing renders
//        // lines on various background
//        Rectangle2D.Double rect = new Rectangle2D.Double ( 150, 50, 250, 150 );
//        g2d.setColor( Color.red );
//        g2d.fill( rect );
//
//        g2d.setColor( Color.black );
//        g2d.setXORMode( Color.red );
//
//        for( int i = 0; i < l_list.size(); i++)
//            g2d.draw( l_list.get( i ) );
//
//        g2d.setPaintMode();
//
//    }
//
//    public void actionPerformed( ActionEvent event)
//    {
//        initLines();
//        repaint();
//    }
//
//    // Mouse event handlers
//    // Funkcje obs³ugi zdarzeñ  myszy
//    public void mouseClicked(MouseEvent arg0)
//    {
//    }
//
//    public void mouseEntered(MouseEvent arg0)
//    {
//    }
//
//    public void mouseExited(MouseEvent arg0)
//    {
//        // Release line if currently being dragged
//        line_caught = -1;
//    }
//
//    public void mousePressed(MouseEvent arg0)
//    {
//        // No line can may be caught at this moment.
//        // Find close end of line to catch
//        if ( CatchClosePoint(  arg0.getX(), arg0.getY() ) )
//        {
//            // Clicked near one of line ends catch it and update position
//            UpdateLinePosition( arg0.getX(), arg0.getY() );
//        }
//        else
//        {
//            l_list.add( new Line2D.Double(arg0.getX(), arg0.getY(), arg0.getX(), arg0.getY()) );
//            point_caught = 1;
//            line_caught = l_list.size() - 1;
//        }
//    }
//
//    public void mouseReleased(MouseEvent arg0)
//    {
//        line_caught = -1;
//    }
//
//    public void mouseDragged(MouseEvent arg0)
//    {
//        if ( line_caught != -1 )
//            UpdateLinePosition( arg0.getX(), arg0.getY() );
//    }
//
//    public void mouseMoved(MouseEvent arg0)
//    {
//    }
//}
//
//class SmpWindow extends JFrame
//{
//    public SmpWindow()
//    {
//        // Acquire drawing surface and add own panel to it
//        // Pozyskanie dostepu do powierzchni rysowania okna
//        Container  contents = getContentPane();
//        contents.add( new DrawWndPane() );
//    }
//}
//
//
//
//
