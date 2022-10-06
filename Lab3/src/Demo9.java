///* Computer graphics courses at Wroclaw University of Technology
// * (C) by Jurek Sas, 2008
// *
// * Description:
// *   This demo explains hot to apply geometric transformations
// *   in 2D using java2D
// *
// *   Ten program demonstracyjny pokazuje jak u¿ywaæ API do przekszta³ceñ
// *   geometrycznych na p³aszczyznie z wykorzystaniem java2D.
// */
//
//import java.awt.Color;
//import java.awt.Container;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.Shape;
//import java.util.Scanner;
//import javax.swing.*;
//
//import java.awt.*;
//import java.awt.geom.*;
//import java.awt.event.*;
//import java.awt.geom.Line2D;
//
//public class Demo9
//{
//    public static void main(String[] args)
//    {
//        Scanner in = new Scanner(System.in);
//
//        SmpWindow wnd = new SmpWindow();
//        wnd.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
//        wnd.setVisible(true);
//        wnd.setBounds( 70, 70, 700, 300);
//        wnd.setTitle( "Geometric transformations by operation concatenation, (C) J.Sas, 2008" );
//    }
//}
//
//class DrawWndPane  extends JPanel implements ActionListener
//{
//
//    JButton button1;
//    JButton button2;
//    JButton button3;
//    JButton button4;
//    JButton button5;
//
//    AffineTransform transform;
//
//
//    DrawWndPane()
//    {
//        super();
//
//        // Switch off automatic components positioning
//        // Wy³¹czanie automatycznego pozycjonowania komponentów
//        setLayout( null );
//
//        // Create components and add it to the panel
//        // Utworzenie komponentów i dodanie ich do panelu okna
//        button1 = new JButton( "Move by X" );
//        button2 = new JButton( "Rotate CCW" );
//        button3 = new JButton( "Shrink" );
//        button4 = new JButton( "Enlarge" );
//        button5 = new JButton( "Reset" );
//        button1.setBounds( 100, 100, 100, 30 );
//        button2.setBounds( 210, 100, 100, 30 );
//        button3.setBounds( 320, 100, 100, 30 );
//        button4.setBounds( 430, 100, 100, 30 );
//        button5.setBounds( 540, 100, 100, 30 );
//        add( button1 );
//        add( button2 );
//        add( button3 );
//        add( button4 );
//        add( button5 );
//
//
//        // Add listener to allow reacting to clicking -
//        // The same listener is used by all buttons
//        // Dodawanie s³uchacza zdarzeñ - tego samego dla wszystkich przycisków
//        button1.addActionListener( this );
//        button2.addActionListener( this );
//        button3.addActionListener( this );
//        button4.addActionListener( this );
//        button5.addActionListener( this );
//
//        // Initially set the transformation as identity
//        transform = new AffineTransform(  1, 0, /* 0 */
//                0, 1, /* 0 */
//                0, 0  /* 1 */ );
//    }
//
//    public void paintComponent( Graphics g)
//    {
//        Graphics2D  g2d = (Graphics2D)g;
//
//        super.paintComponent(g);
//
//        // Set transformation for all further draws
//        g2d.setTransform( transform );
//
//        Line2D.Double  line = new Line2D.Double( 0, 0 , 50, 50 );
//        g2d.draw( line );
//
//        Rectangle2D.Double  rectangle = new Rectangle2D.Double(40, 40, 60, 90);
//        g2d.draw( rectangle );
//
//        // Restore identity transform to avoid incorrect drawings of controls
//        g2d.setTransform( new AffineTransform() );
//    }
//
//    public void actionPerformed( ActionEvent event)
//    {
//        // Acquire reference to the object being affected the event
//        // Pozyskanie referencji do obiektu, z którym to zdarzenie jest zwi¹zane
//        Object source = event.getSource();
//
//        // Distinguish which button has been clicked
//        if ( source == button1 )
//        {
//            // translate right by 10
//            transform.translate(  10, 0 );
//        }
//        else
//        if ( source == button2 )
//        {
//            // set transform for rotation by 10 deg
//            transform.rotate( 10.0 * 3.1415 / 360, 0, 0 );
//            // transform.scale( 2.5, 2.5 );
//        }
//        else
//        if ( source == button3 )
//        {
//            // reduce size
//            transform.scale( 0.5, 0.5 );
//        }
//        else
//        if ( source == button4 )
//        {
//            // extend size
//            transform.scale( 2.0, 2.0 );
//        }
//        else
//        {
//            // reset transformation to none state
//            transform.setToIdentity();
//        }
//
//        // Force window redraw to see the result immediately
//        // Wymuszenie przerysowania okna aby uzyskaæ efekt operacji natychmiast
//        repaint();
//    }
//}
//
//class SmpWindow extends JFrame
//{
//
//    public SmpWindow()
//    {
//        // Acquire drawing surface and add own panel to it
//        // Pozyskanie dostepu do powierzchni rysowania okna
//        Container  contents = getContentPane();
//        contents.add( new DrawWndPane() );
//    }
//
//}
