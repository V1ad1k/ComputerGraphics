///*
// * Computer graphics courses at Wroclaw University of Technology
// * (C) by Jurek Sas, 2008
// *
// * Description:
// *   This demo explains how to programmatically control
// *   complex GUI layouts
// *
// *   Ten program demonstracyjny pokazuje jak programowo
// *   sterowac zlozonymi ukladami GUI
// */
//
//import java.awt.Color;
//import java.awt.Container;
//import java.awt.Graphics;
//import java.util.Scanner;
//import javax.swing.*;
//
//import java.awt.*;
//
//import java.awt.event.*;
//import java.awt.geom.AffineTransform;
//import java.awt.geom.Line2D;
//import java.awt.geom.Rectangle2D;
//
//public class DemoMP
//{
//    public static void main(String[] args)
//    {
//        // TODO Auto-generated method stub
//        SmpWindow wnd = new SmpWindow();
//        wnd.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
//        wnd.setVisible(true);
//        wnd.setBounds( 70, 70, 650, 500);
//        wnd.setTitle( "Manual layouting demo, (C) J.Sas, 2012" );
//    }
//}
//
//class DrawWndPane extends JPanel
//        implements MouseListener, MouseMotionListener
//{
//    JButton button1;
//    JButton button2;
//    JButton button3;
//
//    AffineTransform transform, inverse;
//    int ident;
//
//    DrawWndPane()
//    {
//        super();
//
//        // Switch off automatic components positioning
//        // Wy��czanie automatycznego pozycjonowania komponent�w
//        setLayout( null );
//        setBackground( new Color( 255, 0, 0 ));
//    }
//    DrawWndPane( Color c, int id)
//    {
//        super();
//
//        // Switch  automatic components positioning on
//        // W��czanie automatycznego pozycjonowania komponent�w
//        setLayout( new GridLayout(2,2) );
//        setBackground( c );
//
//        button1 = new JButton( "1" );
//        button2 = new JButton( "2" );
//        button3 = new JButton( "3" );
//        button1.setBackground( c );
//
//        transform = new AffineTransform();
//        // add( button1);
//        // add( button2);
//        // add( button3);
//
//        ident = id;
//
//        addMouseListener( this );
//        addMouseMotionListener( this );
//    }
//
//    public void paintComponent (Graphics g )
//    {
//        Graphics2D g2d = (Graphics2D) g;
//        super.paintComponent(g);
//
//
//
//        // transform.rotate( 3.0 );
//        // Set transformation for all further draws
//        // g2d.transform( transform );
////        Line2D.Double line = new Line2D.Double(d.width, 0, 50, 50);
////        contents.add( /* transform.createTransformedShape */ ( line ) );
//
////
////        Rectangle2D.Double rectangle = new Rectangle2D.Double(00, 0, 60, 90);
////        g2d.draw(  /* transform.createTransformedShape */ ( rectangle ) );
//
//
//    }
//
//
//    // Mouse event handlers
//    // Funkcje obs�ugi zdarze�  myszy
//    public void mouseClicked(MouseEvent arg0)
//    {
//        System.out.println( "mouseClicked at " + arg0.getX() + "  " + arg0.getY() + " id " + ident );
//    }
//
//    public void mouseEntered(MouseEvent arg0)
//    {
//        System.out.println( "mouseEntered at " + arg0.getX() + "  " + arg0.getY()+ " id " + ident  );
//    }
//
//    public void mouseExited(MouseEvent arg0)
//    {
//        System.out.println( "mouseExited at " + arg0.getX() + "  " + arg0.getY() + " id " + ident );
//    }
//
//    public void mousePressed(MouseEvent arg0)
//    {
//        String which;
//
//        which = "";
//        if ( arg0.getButton() == MouseEvent.BUTTON1)
//            which = " Button 1";
//        if ( arg0.getButton() == MouseEvent.BUTTON2)
//            which = " Button 2";
//        if ( arg0.getButton() == MouseEvent.BUTTON3)
//            which = " Button 3";
//
//        System.out.println( "mousePressed button "  + arg0.getButton() + " at " + arg0.getX() + "  " + arg0.getY() + " " + which + " id " + ident );
//    }
//
//    public void mouseReleased(MouseEvent arg0)
//    {
//        System.out.println( "mouseReleased at " + arg0.getX() + "  " + arg0.getY() + " id " + ident );
//    }
//
//    public void mouseDragged(MouseEvent arg0)
//    {
//        System.out.println( "mouseDragged at " + arg0.getX() + "  " + arg0.getY()+ " id " + ident  );
//    }
//
//    public void mouseMoved(MouseEvent arg0)
//    {
//        System.out.println( "mouseMoved to " + arg0.getX() + "  " + arg0.getY()+ " id " + ident  );
//    }
//
//}
//
//class SmpWindow extends JFrame
//{
//    DrawWndPane p1, p2,p3,p4;
//    public SmpWindow()
//    {
//
//        // Acquire drawing surface and add own panel to it
//        // Pozyskanie dostepu do powierzchni rysowania okna
//        Container  contents = getContentPane();
//        contents.setLayout( null );
//        //contents.setLayout( new BorderLayout() );
//        p1 = new DrawWndPane( new Color( 200, 200, 200) , 1);
//        p2 = new DrawWndPane( new Color( 150, 255, 200), 2 );
//        p3 = new DrawWndPane( new Color( 200, 200, 200), 3 );
//        //p4 = new DrawWndPane( new Color( 0, 0, 100) );
//        p1.setBounds( 100, 100, 100, 300 );
//        p2.setBounds( 210, 100, 100, 300 );
//        p3.setBounds( 310, 100, 100, 300 );
//        //p4.setBounds( 310, 100, 100, 300 );
//
//        contents.add( p1 );
//        contents.add( p2 );
//        contents.add( p3 );
//        //contents.add( p4 );
//    }
//
//    public void paint (Graphics g )
//    {
//        Container  contents = getContentPane();
//        Rectangle d = contents.getBounds();
//
//        //Left
//        p1.setBounds( 0, d.height/2, d.width/2, d.height/2 );
//
//        //Center
//        p2.setBounds( 0, 0, d.width/2, d.height/2);
//
//        //Bottom
//        p3.setBounds( d.width/2, 0, d.width/2, d.height );
//        super.paint( g );
//    }
//}