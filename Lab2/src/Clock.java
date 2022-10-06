import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import java.util.Scanner;

import java.awt.*;
import java.awt.geom.*;
//import java.awt.Color;
//import java.awt.BasicStroke;
//import java.awt.Container;
//import java.awt.Dimension;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.Polygon;

import java.lang.Thread;
import java.lang.InterruptedException;

import javax.swing.*;

public class Clock {

    public static void main(String[] args)
    {
        double amplitude = Double.parseDouble(args[0]);
        double period = Double.parseDouble(args[1]);
        SmpWindow wnd = new SmpWindow(amplitude,period);

        // Closing window trerminates the program
        wnd.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        wnd.setBounds( 60, 60, 300, 300);
        wnd.setVisible(true);


        while ( true )
        {
            try
            {
                Thread.sleep( 5000 );
            }
            catch ( InterruptedException e )
            {
                System.out.println("Interrupted");
            }
            wnd.repaint();

        }


    }

}

class DrawWndPane extends JPanel
{

    final int GAUGE_LEN = 10;
    int  center_x, center_y;
    int  r_outer, r_inner;
    GregorianCalendar calendar;
    private double amplitude;
    private double period;

    DrawWndPane(double ampl, double per)
    {
        super();
        this.amplitude = ampl;
        this.period = per;
        setBackground( new Color( 200, 200, 255) );
        calendar = new GregorianCalendar();
    }

    public void DrawGauge( double angle, Graphics g )
    {
        int xw, yw, xz, yz;

        angle = 3.1415 * angle / 180.0;
        xw = (int)(center_x + r_inner/2 * Math.sin( angle ));
        yw = (int)(center_y - r_inner/2 * Math.cos( angle ));
        xz = (int)(center_x + r_outer/2 * Math.sin( angle ));
        yz = (int)(center_y - r_outer/2 * Math.cos( angle ));

        g.drawLine( xw, yw, xz, yz );
    }

    public void DrawHand( double angle, int length, Graphics g )
    {
        int xw, yw, xz, yz;

        angle = 3.1415 * angle / 180.0;
        xw = (int)(center_x + length * Math.sin( angle )/2);
        yw = (int)(center_y - length * Math.cos( angle )/2);

        angle += 3.1415;
        xz = (int)(center_x + GAUGE_LEN * Math.sin( angle )/2);
        yz = (int)(center_y - GAUGE_LEN * Math.cos( angle )/2);

        g.drawLine( xw, yw, xz, yz );
    }


    public void DrawPendilum( double angle, int length, Graphics g, int divider )
    {
        int xw, yw, xz, yz;

        angle = 3.1415 * angle / 180.0;
        xw = (int)(center_x + length * Math.sin( angle )/divider);
        yw = (int)(center_y - length * Math.cos( angle )/divider);

        angle += 3.1415;
        xz = (int)(center_x + GAUGE_LEN * Math.sin( angle )/divider);
        yz = (int)(center_y - GAUGE_LEN * Math.cos( angle )/divider);
        g.drawLine( xw, yw, xz, yz );
        g.drawOval(xw-8, yw, xz/10, yz/10);
    }

    public void DrawDial( Graphics g )
    {
        g.drawOval(  center_x - r_outer/2,
                center_y - r_outer/2,
                r_outer, r_outer );

        for ( int i = 0; i <= 11; i++ )
            DrawGauge( i * 30.0, g );
    }
    public void paintComponent( Graphics g)
    {
        int  minute, second, hour;
        super.paintComponent(g);
        Graphics2D  g2d = (Graphics2D)g;

        Dimension size = getSize();

        center_x = size.width/2;
        center_y = size.height/2;
        r_outer = Math.min( size.width, size.height)/2;
        r_inner = r_outer - GAUGE_LEN;

        Date time = new Date();
        calendar.setTime( time );

        minute = calendar.get( Calendar.MINUTE );
        hour   = calendar.get( Calendar.HOUR );
        if ( hour > 11 )
            hour = hour - 12;
        second = calendar.get( Calendar.SECOND );

        DrawDial( g );

        g2d.setColor( new Color( 255, 0, 0 ) );
        g2d.setStroke( new BasicStroke( 5 ) );
        DrawHand( 360.0 * (hour * 60 + minute) / ( 60.0 * 12 ) , (int)(0.75 * r_inner), g);

        g2d.setColor( new Color( 255, 0, 0 ) );
        g2d.setStroke( new BasicStroke( 3 ) );
        DrawHand( 360.0 * (minute * 60 + second )/( 3600.0), (int)(0.97 * r_outer), g);

        g2d.setColor( new Color( 0, 0, 0 ) );
        g2d.setStroke( new BasicStroke( 1 ) );
        DrawHand( second * 6.0, (int)(0.97 * r_inner), g);

        g2d.setColor( new Color( 150, 255, 0 ) );
        g2d.setStroke( new BasicStroke( 3 ) );
        DrawPendilum( 360.0 * amplitude * (second * 6.0 + second )/period, (int)(0.97 * r_outer), g, 1);
        // g.drawOval( (int)(0.77 * r_outer), (int)(0.77 * r_outer), (int)(0.97 * r_outer), (int)(0.97 * r_outer));
    }
}

class SmpWindow extends JFrame
{

    public SmpWindow(double ampl, double per)
    {
        Container  contents = getContentPane();
        contents.add( new DrawWndPane(ampl, per) );
        setTitle( "Clock");
    }
}