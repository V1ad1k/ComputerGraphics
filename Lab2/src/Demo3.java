//
//import java.awt.Color;
//import java.awt.Container;
//import java.awt.Graphics;
//import java.util.ArrayList;
//import java.util.Scanner;
//import javax.swing.*;
//import java.awt.*;
//
//import java.awt.event.*;
//
//public class Demo3 {
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
//        wnd.setTitle( "Mouse event handling demo, (C) J.Sas, 2008" );
//    }
//
//}
//
//// This class implements mouse and keyboard listeners
//@SuppressWarnings("serial")
//class DrawWndPane extends JPanel
//        implements ActionListener, MouseListener, MouseMotionListener, KeyListener
//{
//
//    JButton button1;
//    JButton button2;
//    JButton button3;
//
//    String  message;
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
//        button1 = new JButton( "1" );
//        button2 = new JButton( "2" );
//        button3 = new JButton( "3" );
//        button1.setBounds( 100, 100, 70, 30 );
//        button2.setBounds( 190, 100, 70, 30 );
//        button3.setBounds( 280, 100, 70, 30 );
//        add( button1 );
//        add( button2 );
//        add( button3 );
//
//       /*
//       JButton button4 = new JButton( "4" );
//       JButton button5 = new JButton( "5" );
//
//       JPanel  insider;
//       insider = new JPanel();
//       //insider.setLayout( null );
//
//       insider.setBounds( 00 , 200, 100 , 100 );
//       button4.setBounds( 00 , 0, 100 , 100 );
//
//       insider.add( button4 );
//       insider.add( button5 );
//
//       add(insider);
//       */
//
//        message = "No button has been pressed yet";
//
//        // Add listener to allow reacting to clicking -
//        // The same listener is used by all buttons
//        // Dodawanie s³uchacza zdarzeñ - tego samego dla wszystkich przycisków
//        button1.addActionListener( this );
//        button2.addActionListener( this );
//        button3.addActionListener( this );
//
//
//        // mate this object also mouse event listener
//        // Skierowanie obs³ugi zdarzeñ zwi¹zanyhc z mysz¹ do tego samego
//        // obiektu
//        addMouseListener( this );
//        addMouseMotionListener( this );
//        addKeyListener( this );
//        setFocusable( true );
//
//    }
//
//    public void paintComponent( Graphics g)
//    {
//        super.paintComponent(g);
//        Graphics2D  g2d = (Graphics2D)g;
//        g2d.drawString( message, 130, 150);
//
//    }
//
//    @Override
//    public void mouseClicked(MouseEvent arg0) {
//        ArrayList<Point> points;
//        points = new ArrayList<Point>();
//        points.add(new Point(arg0.getX(), arg0.getY()));
//        repaint();
//    }
//    public void actionPerformed( ActionEvent event)
//    {
//        // Acquire reference to the object being affected the event
//        // Pozyskanie referencji do obiektu, z którym to zdarzenie jest zwi¹zane
//        Object source = event.getSource();
//
//        // Distinguish which button has been clicked
//        if ( source == button1 )
//            message = "Button 1 clicked";
//        else
//        if ( source == button2 )
//            message = "Button 2 clicked";
//        else
//            message = "Button 3 clicked";
//
//        // Force window redraw to see the result immediately
//        // Wymuszenie przerysowania okna aby uzyskaæ efekt operacji natychmiast
//        repaint();
//    }
//    public void draw(MouseEvent arg0, Graphics g){
//        g.drawOval(arg0.getX(), arg0.getY(), 10,10);
//    }
//    // Mouse event handlers
//    // Funkcje obs³ugi zdarzeñ  myszy
////    public void mouseClicked(MouseEvent arg0)
////    {
////        System.out.println( "mouseClicked at " + arg0.getX() + "  " + arg0.getY() );
////    }
//
//    public void mouseEntered(MouseEvent arg0)
//    {
//        System.out.println( "mouseEntered at " + arg0.getX() + "  " + arg0.getY() );
//    }
//
//    public void mouseExited(MouseEvent arg0)
//    {
//        System.out.println( "mouseExited at " + arg0.getX() + "  " + arg0.getY() );
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
//        System.out.println( "mousePressed button "  + arg0.getButton() + " at " + arg0.getX() + "  " + arg0.getY() + " " + which );
//    }
//
//    public void mouseReleased(MouseEvent arg0)
//    {
//        System.out.println( "mouseReleased at " + arg0.getX() + "  " + arg0.getY() );
//    }
//
//    public void mouseDragged(MouseEvent arg0)
//    {
//        System.out.println( "mouseDragged at " + arg0.getX() + "  " + arg0.getY() );
//    }
//
//    public void mouseMoved(MouseEvent arg0)
//    {
//        System.out.println( "mouseMoved to " + arg0.getX() + "  " + arg0.getY() );
//    }
//    public void keyReleased(KeyEvent e)
//    {
//
//        System.out.println( "keyReleased" );
//    }
//    public void keyTyped(KeyEvent e)
//    {
//        System.out.println( "keyTyped" );
//    }
//    // This handler is called when the keyboard key is pressed
//    public void keyPressed(KeyEvent e)
//    {
//        // Use getKeyCode to get the symbol of the pressed key.
//        // It can be used to distinguish virtual keys.
//        // Use getKeyChar() to get the character associated
//        // with the key. Use it for "ordinary" character keys.
//        System.out.println( "keyPressed " +
//                " Key code: " + e.getKeyCode() +
//                " Char: "     + e.getKeyChar() );
//    }
//
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
//
//
//
//
