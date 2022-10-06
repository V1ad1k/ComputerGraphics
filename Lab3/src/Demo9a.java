/* Computer graphics courses at Wroclaw University of Technology
 * (C) by Jurek Sas, 2008
 *
 * Description:
 *   This demo explains hot to apply geometric transformations
 *   in 2D using java2D
 *
 *   Ten program demonstracyjny pokazuje jak u�ywa� API do przekszta�ce�
 *   geometrycznych na p�aszczyznie z wykorzystaniem java2D.
 */
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Demo9a
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        SmpWindow wnd = new SmpWindow();
        wnd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        wnd.setVisible(true);
        wnd.setBounds(70, 70, 1000, 800);
        wnd.setTitle("Geometric transformations by operation concatenation, (C) J.Sas, 2008");
    }
}

class ImgRectangle extends Rectangle2D.Double
{
    static final int DEF_RECT_SIZE_X = 180;
    static final int DEF_RECT_SIZE_Y = 270;

    enum RectVertices  { UPPER_LEFT, UPPER_RIGHT, LOWER_LEFT, LOWER_RIGHT, NONE_VERT };
    enum OpModes  { OP_ROTATION, OP_SCALING, OP_NONE };

    boolean      rect_caught;
    RectVertices vert_caught = RectVertices.NONE_VERT;
    OpModes      op_mode     = OpModes.OP_NONE;

    int rect_size_x, rect_size_y;

    BufferedImage img;
    public AffineTransform transform, inverse;

    ImgRectangle( String img_fname )
    {
        super( 0, 0, DEF_RECT_SIZE_X, DEF_RECT_SIZE_Y );
        try
        {
            img = ImageIO.read( new File( img_fname ) );
        }
        catch (IOException e) {
            img = null;
            rect_size_x = DEF_RECT_SIZE_X;
            rect_size_y = DEF_RECT_SIZE_Y;
        }

        transform = new AffineTransform( 1, 0, /* 0 */
                0, 1, /* 0 */
                0, 0 /* 1 */);

        // Scale it to fit the fixed rectangle
        rect_size_y = img.getHeight();
        rect_size_x = img.getWidth();

        setRect( 0, 0, rect_size_x, rect_size_y );

        double  init_scale = DEF_RECT_SIZE_Y / (double)rect_size_y;
        transform.setToScale( init_scale, init_scale );

        try {
            inverse = transform.createInverse();
        }
        catch (Exception x){ inverse = null; }
    }

    // This method draws the whole shape elements
    void drawWhole( Graphics2D g2d )
    {
        // Assign the shape transformation to the drawing context
        g2d.transform( transform );

        g2d.draw( this );
        if ( img != null )
            g2d.drawImage( img, 0, 0, null );

        // Restore identity transform to avoid incorrect drawings of controls
        g2d.setTransform(new AffineTransform());
    }

    // This method tests if passed point in panel coordinates is close to one of rectangle vertices
    boolean  catchRectangle( int x, int y )
    {
        // Calculate the position of the point in local shape coordinates
        Point2D.Double p1 = new java.awt.geom.Point2D.Double( x, y );
        Point2D.Double p = new java.awt.geom.Point2D.Double(0, 0);
        inverse.transform(p1, p);
        System.out.println( p.x + " " + p.y);

        // Find the sensitivity area in the local shape coordinates
        double mtx [] = new double[9];
        transform.getMatrix( mtx );

        double sc_x = mtx[0]*mtx[0] + mtx[1]*mtx[1];
        double tolerance = DrawWndPane.SENS_DIST / sc_x;

        if (( Math.abs( p.x ) < tolerance ) && ( Math.abs( p.y ) < tolerance ))
        {
            rect_caught = true;
            vert_caught = RectVertices.UPPER_LEFT;
            return true;
        }
        System.out.println( "Comp: " + (p.x - rect_size_x)  + " tol " + tolerance);
        if (( Math.abs( p.x - rect_size_x ) < tolerance ) && ( Math.abs( p.y ) < tolerance ))
        {
            rect_caught = true;
            vert_caught = RectVertices.UPPER_RIGHT;
            System.out.println( "UR ");
            return true;
        }
        if (( Math.abs( p.x - rect_size_x ) < tolerance ) && ( Math.abs( p.y - rect_size_y ) < tolerance ))
        {
            rect_caught = true;
            vert_caught = RectVertices.LOWER_RIGHT;
            return true;
        }
        if (( Math.abs( p.x ) < tolerance ) && ( Math.abs( p.y - rect_size_y ) < tolerance ))
        {
            rect_caught = true;
            vert_caught = RectVertices.LOWER_LEFT;
            return true;
        }

        // If we are there then no rectangle vertex is close enough
        rect_caught = false;
        vert_caught = RectVertices.NONE_VERT;

        return false;
    }

    double rotateShapeByPoints(
            Point2D.Double p_p_panel, // mouse press point - start position for rotation
            Point2D.Double r_p_panel  // mouse release point - end position for rotation
    )
    {
        AffineTransform inverse;
        try {
            inverse = transform.createInverse();
        }
        catch (Exception x) { return 0; }

        // First find the position of the press point in local rectanglecoords
        Point2D.Double p_p = new java.awt.geom.Point2D.Double(0, 0);
        inverse.transform( p_p_panel, p_p);

        // Find the coords of the release point in local coords
        Point2D.Double p_r = new java.awt.geom.Point2D.Double(0, 0);
        inverse.transform( r_p_panel, p_r);

        // Find the shape center - for your own shapes you nned to owerwrite this method
        Rectangle2D bb = getBounds2D();
        double shape_center_x = bb.getCenterX();
        double shape_center_y = bb.getCenterY();

        // Find corresponding vectors
        Point2D.Double v_p = new java.awt.geom.Point2D.Double(
                p_p.x - shape_center_x,
                p_p.y - shape_center_y );
        Point2D.Double v_r = new java.awt.geom.Point2D.Double(
                p_r.x - shape_center_x,
                p_r.y - shape_center_y );

        // Compute corresponding angles
        double a_p = Math.atan2( v_p.y, v_p.x);
        if ( a_p < 0 )
            a_p = 2*Math.PI + a_p;
        // Now a_p is in the range <0 2*pi)

        double a_r = Math.atan2( v_r.y, v_r.x);
        if ( a_p < 0 )
            a_p = 2*Math.PI + a_p;
        // Now a_r is in the range <0 2*pi)

        double  a_rotation = a_r - a_p;

        // Now we are ready to rotate
        // =======================================================
        // set transform for rotation by a_rotation
        // Find center
        Point2D.Double pc = new java.awt.geom.Point2D.Double(shape_center_x, shape_center_y);
        Point2D.Double p2 = new java.awt.geom.Point2D.Double(0, 0);
        transform.transform(pc, p2);
        AffineTransform new_transform = new AffineTransform(  1, 0, /* 0 */
                0, 1, /* 0 */
                0, 0 /* 1 */);
        new_transform.rotate( a_rotation, p2.x, p2.y);
        new_transform.concatenate(transform);

        transform.setTransform( new_transform );

        try {
            this.inverse = transform.createInverse();
        }
        catch (Exception x) { return 0; }

        return a_rotation;
    }

    double scaleShapeByPoints(
            Point2D.Double p_p_panel, // mouse press point - start position for rotation
            Point2D.Double r_p_panel  // mouse release point - end position for rotation
    )
    {
        AffineTransform inverse;
        try {
            inverse = transform.createInverse();
        }
        catch (Exception x) { return 0; }

        // First find the position of the press point in local rectanglecoords
        Point2D.Double p_p = new java.awt.geom.Point2D.Double(0, 0);
        inverse.transform( p_p_panel, p_p);

        // Find the coords of the release point in local coords
        Point2D.Double p_r = new java.awt.geom.Point2D.Double(0, 0);
        inverse.transform( r_p_panel, p_r);

        // Find the shape center - for your own shapes you need to owerwrite this method
        Rectangle2D bb = getBounds2D();
        double shape_center_x = bb.getCenterX();
        double shape_center_y = bb.getCenterY();

        // Find corresponding vectors
        Point2D.Double v_p = new java.awt.geom.Point2D.Double(
                p_p.x - shape_center_x,
                p_p.y - shape_center_y );
        Point2D.Double v_r = new java.awt.geom.Point2D.Double(
                p_r.x - shape_center_x,
                p_r.y - shape_center_y );

        double v_p_len = Math.sqrt( v_p.x*v_p.x + v_p.y*v_p.y );
        double v_r_len = Math.sqrt( v_r.x*v_r.x + v_r.y*v_r.y );

        double scale = v_r_len / v_p_len;

        // Now we are ready to scale
        // =======================================================
        // set transform for scaling by scale
        // Find center

        Point2D.Double pc = new java.awt.geom.Point2D.Double(shape_center_x, shape_center_y);
        Point2D.Double p2 = new java.awt.geom.Point2D.Double(0, 0);
        transform.transform(pc, p2);
        AffineTransform new_transform = new AffineTransform(  1, 0, /* 0 */
                0, 1, /* 0 */
                0, 0 /* 1 */);
        new_transform.translate( p2.x, p2.y);
        new_transform.scale( scale, scale);
        new_transform.translate( -p2.x, -p2.y);
        new_transform.concatenate(transform);

        transform.setTransform( new_transform );

        try {
            this.inverse = transform.createInverse();
        }
        catch (Exception x) { return 0; }

        return scale;
    }

    void translate( int x_t, int y_t )
    {
        AffineTransform new_transform = new AffineTransform(1, 0, /* 0 */
                0, 1, /* 0 */
                0, 0 /* 1 */);
        new_transform.translate( x_t, y_t);
        new_transform.concatenate(transform);
        transform = new_transform;
        // transform.concatenate( new_transform);
        try {
            inverse = transform.createInverse();
        }
        catch (Exception x) {}
    }


    void rotateByDegs( double angle )
    {
        Point2D.Double p1 = new java.awt.geom.Point2D.Double( rect_size_x/2, rect_size_y/2 );
        Point2D.Double p2 = new java.awt.geom.Point2D.Double(0, 0);
        transform.transform(p1, p2);
        AffineTransform new_transform = new AffineTransform(  1, 0, /* 0 */
                0, 1, /* 0 */
                0, 0 /* 1 */);
        new_transform.rotate( angle * 2 * Math.PI / 360, p2.x, p2.y);
        new_transform.concatenate(transform);

        transform = new_transform;
        try
        {
            inverse = transform.createInverse();
        }
        catch (Exception x)
        {
        }
    }

    void scale( double s_xy )
    {
        Point2D.Double p1 = new java.awt.geom.Point2D.Double( rect_size_x/2, rect_size_y/2 );
        Point2D.Double p2 = new java.awt.geom.Point2D.Double(0, 0);
        transform.transform(p1, p2);

        AffineTransform new_transform = new AffineTransform(  1, 0, /* 0 */
                0, 1, /* 0 */
                0, 0 /* 1 */);
        new_transform.translate( p2.x, p2.y);
        new_transform.scale( s_xy, s_xy );
        new_transform.translate( -p2.x, -p2.y);
        new_transform.concatenate(transform);

        transform = new_transform;

        // reduce size
        // transform.scale(0.5, 0.5);
        try
        {
            inverse = transform.createInverse();
        }
        catch (Exception x)
        {
        }
    }

    void reset()
    {
        double  init_scale = DEF_RECT_SIZE_Y / (double)rect_size_y;
        transform.setToScale( init_scale, init_scale );

        try {
            inverse = transform.createInverse();
        }
        catch (Exception x){ inverse = null; }
    }
}

class DrawWndPane extends JPanel implements ActionListener, MouseListener,
        MouseMotionListener
{
    JButton button1;
    JButton button2;
    JButton button3;
    JButton button4;
    JButton button5;
    JButton button6;
    AffineTransform transform, inverse;

    int    m_press_x, m_press_y;

    static public final int  SENS_DIST = 10;

    enum OpModes  { OP_ROTATION, OP_SCALING, OP_NONE };

    OpModes      op_mode     = OpModes.OP_NONE;

    ImgRectangle rectangle;

    BufferedImage   bckg;

    DrawWndPane()
    {
        super();

        // Just to have variables initialized as "no press" event\
        m_press_x = m_press_y = -10000;

        // Switch off automatic components positioning
        // Wy��czanie automatycznego pozycjonowania komponent�w
        setLayout(null);

        // Create components and add it to the panel
        // Utworzenie komponent�w i dodanie ich do panelu okna
        button1 = new JButton("Move by X");
        button2 = new JButton("Rotate CCW");
        button3 = new JButton("Shrink");
        button4 = new JButton("Enlarge");
        button5 = new JButton("Reset");
        button6 = new JButton("Move by Y");

        button1.setBounds(100, 100, 100, 30);
        button6.setBounds(210, 100, 100, 30);
        button2.setBounds(320, 100, 100, 30);
        button3.setBounds(430, 100, 100, 30);
        button4.setBounds(540, 100, 100, 30);
        button5.setBounds(650, 100, 100, 30);

        add(button1);
        add(button2);
        add(button3);
        add(button4);
        add(button5);
        add(button6);

        // Add listener to allow reacting to clicking -
        // The same listener is used by all buttons
        // Dodawanie s�uchacza zdarze� - tego samego dla wszystkich przycisk�w
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        button5.addActionListener(this);
        button6.addActionListener(this);

        addMouseListener(this);
        addMouseMotionListener(this);

        // Here we use common transformation matrix; in case
        // of the image consisting of many elements you need to store matrices
        // associated with individual shapes
        transform = new AffineTransform( 1, 0, /* 0 */
                0, 1, /* 0 */
                0, 0 /* 1 */);

        // In this demo the image contents are limited to a single rectangle
        // Change the image name according to local image location
        rectangle = new ImgRectangle( "C:\\Users\\Vlad\\Desktop\\roster.jpg" );

        try
        {
            bckg = ImageIO.read( new File( "C:\\Users\\Vlad\\Desktop\\photographer.jpg"  ) );
        }
        catch (IOException e) {
            bckg = null;
        }

        try
        {
            inverse = transform.createInverse();
        }
        catch (Exception x)
        {
        }
    }

    public void paintComponent(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g);

        Dimension size = getSize();

      if ( bckg != null )
         g2d.drawImage( bckg, 0,  0, size.width, size.height,
                              0,  0, bckg.getWidth(),  bckg.getHeight(), null);


        // Draw all component shapes - in this demo we have only single one
        rectangle.drawWhole( g2d );
    }

    public void mouseClicked(MouseEvent arg0)
    {
    }

    public void mouseEntered(MouseEvent arg0)
    {
    }

    public void mouseExited(MouseEvent arg0)
    {
        rectangle.rect_caught = false;
    }

    public void mousePressed(MouseEvent arg0)
    {
        m_press_x =  arg0.getX();
        m_press_y =  arg0.getY();

        // Check is mouse position near the vertex of the rectangle
        // and set appropriate operation mode (scale or rotate)
        if ( rectangle.catchRectangle( m_press_x, m_press_y ) )
            if ( arg0.getButton() == MouseEvent.BUTTON1 )
                op_mode = OpModes.OP_ROTATION;
            else
                op_mode = OpModes.OP_SCALING;
    }

    public void mouseReleased(MouseEvent arg0)
    {
        if ( rectangle.rect_caught )
        {
            // Create two 2D points representing start and end position for the current mini-move
            Point2D.Double p_p_panel = new java.awt.geom.Point2D.Double( m_press_x, m_press_y );
            Point2D.Double r_p_panel = new java.awt.geom.Point2D.Double(arg0.getX(), arg0.getY());

            // Modify shape transform according to current operation mode
            if ( op_mode == OpModes.OP_ROTATION )
                rectangle.rotateShapeByPoints(  p_p_panel, r_p_panel );
            else
                rectangle.scaleShapeByPoints( p_p_panel, r_p_panel );

            rectangle.rect_caught = false;
            repaint();
        }
    }

    public void mouseDragged(MouseEvent arg0)
    {
        if ( rectangle.rect_caught )
        {
            // Create two 2D points representing start and end position for the current mini-move
            Point2D.Double p_p_panel = new java.awt.geom.Point2D.Double( m_press_x, m_press_y );
            Point2D.Double r_p_panel = new java.awt.geom.Point2D.Double(arg0.getX(), arg0.getY());

            // Modify shape transform according to current operation mode
            if ( op_mode == OpModes.OP_ROTATION )
                rectangle.rotateShapeByPoints( p_p_panel, r_p_panel );
            else
                rectangle.scaleShapeByPoints( p_p_panel, r_p_panel );

            // Set current mouse position to be the start position
            // for the next mini-move
            m_press_x = arg0.getX();
            m_press_y = arg0.getY();

            repaint();
        }
    }

    public void mouseMoved(MouseEvent arg0)
    {
    }

    public void actionPerformed(ActionEvent event)
    {
        // Acquire reference to the object being affected the event
        // Pozyskanie referencji do obiektu, z kt�rym to zdarzenie jest zwi�zane
        Object source = event.getSource();
        // Distinguish which button has been clicked and execute coresponding operation
        if (source == button1)
            rectangle.translate( 10, 0 );
        else if (source == button6)
            rectangle.translate( 0, 10 );
        else if (source == button2)
            rectangle.rotateByDegs( 10 );
        else if (source == button3)
            rectangle.scale( 0.5 );
        else if (source == button4)
            rectangle.scale( 2.0 );
        else
            // reset transformation to none state
            rectangle.reset();transform.setToIdentity();

        // Force window redraw to see the result immediately
        // Wymuszenie przerysowania okna aby uzyska� efekt operacji natychmiast
        repaint();
    }
}

class SmpWindow extends JFrame
{
    public SmpWindow()
    {
        // Acquire drawing surface and add own panel to it
        // Pozyskanie dostepu do powierzchni rysowania okna
        Container contents = getContentPane();
        contents.add(new DrawWndPane());
    }
}