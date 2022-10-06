//
//import java.awt.Color;
//import java.awt.Container;
//import java.awt.Graphics;
//import java.util.Scanner;
//import javax.swing.*;
//import java.awt.*;
//import java.awt.BasicStroke;
//
//import java.awt.event.*;
//
//class  C1
//{
//    public String s1;
//}
//
//public class Demo2 {
//
//    /**
//     * @param args
//     */
//    public static C1 c1;
//
//    public static void main(String[] args)
//    {
//        Scanner in = new Scanner(System.in);
//
//        c1 = new C1();
//
//        SmpWindow wnd = new SmpWindow();
//        wnd.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
//        wnd.setVisible(true);
//        wnd.setBounds( 70, 70, 450, 300);
//        wnd.setTitle( "Mouse event handling demo, (C) J.Sas, 2008" );
//    }
//}
//
//class DrawWndPane  extends JPanel implements ActionListener, FocusListener
//{
//    JButton    button1;
//    JButton    button2;
//    JButton    button3;
//    JTextField tfield;
//    int        repaint_cnt;
//    String     message;
//
//    DrawWndPane()
//    {
//        super();
//
//        setLayout( null );
//
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
//        message = "No button has been pressed yet";
//
//        button1.addActionListener( this );
//        button2.addActionListener( this );
//        button3.addActionListener( this );
//
//        tfield = new JTextField( "Start text" );
//        tfield.setBounds( 100, 50, 150, 30 );
//        add( tfield );
//
//        tfield.addFocusListener( this );
//
//        repaint_cnt = 0;
//    }
//
//    public void paintComponent( Graphics g)
//    {
//        super.paintComponent(g);
//        Graphics2D  g2d = (Graphics2D)g;
//
//        repaint_cnt++;
//        System.out.println("Panel repaint requested " + repaint_cnt + " times");
//        g2d.drawString( message, 130, 150);
//    }
//
//
//    public void actionPerformed( ActionEvent event)
//    {
//        Object source = event.getSource();
//
//        if ( source == button1 )
//            message = "Button 1 clicked: " + tfield.getText();
//        else
//        if ( source == button2 )
//            message = "Button 2 clicked";
//        else
//            message = "Button 3 clicked";
//
//
//        System.out.println("Button click detected");
//        repaint();
//    }
//
//    public void focusGained(FocusEvent e) {
//        message = "Focus gained";
//        repaint();
//    }
//
//    public void focusLost(FocusEvent e) {
//        message = "Focus lost " + tfield.getText();
//        repaint();
//    }
//}
//
//class SmpWindow extends JFrame
//{
//
//    public SmpWindow()
//    {
//        Container  contents = getContentPane();
//        contents.add( new DrawWndPane() );
//    }
//
//}
//
//
//
