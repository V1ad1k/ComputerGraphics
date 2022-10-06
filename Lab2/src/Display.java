//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class TaskMod extends JPanel implements ActionListener {
//    int up = 0;
//    int down = 500;
//    double ballx = 500;
//    double bally = 500;
//    char ballDirection;
//    Rectangle border;
//    static Rectangle borderEast;
//    static Rectangle borderNorth;
//    static Rectangle borderSouth;
//    static Rectangle borderWest;
//    static boolean gameOver;
//    Timer timer;
//    Paddle p;
//    Ball b;
//
//    Display() {
//        p = new Paddle();
//        b = new Ball();
//        up = p.up;
//        down = p.down;
//        ballx =  b.ballx;
//        bally =  b.bally;
//        ballDirection = b.ballDirection;
//        initTimer();
//        b.startBall();
//        addKeyListener(p);
//        setFocusable(true);
//
//
//    }
//
//
//    public void initTimer() {
//        timer = new Timer(10, this);
//        timer.start();
//    }
//
//    public void setUpBorders(Graphics2D g2d) {
//        border = new Rectangle(0, 0, Window.length, Window.height);
//        borderEast = new Rectangle(Window.length, 0, 2, Window.height);
//        borderWest = new Rectangle(0, 0, 2, Window.height);
//        borderSouth = new Rectangle(0, Window.height, Window.length, 2);
//        borderNorth = new Rectangle(0, 0, Window.length, 2);
//        g2d.setColor(Color.RED);
//        g2d.draw(border);
//
//    }
//
//    public void paintPaddle(Graphics2D g2d) {
//        g2d.setColor(new Color(0, 130, 130));
//        g2d.fill(p.paddle);
//
//    }
//
//    public void paintBall(Graphics2D g2d) {
//
//        g2d.setColor(new Color(0, 130, 130));
//        g2d.fillOval((int) ballx, (int) bally, 20, 20);
//
//    }
//
//
//
//    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        setBackground(Color.BLACK);
//        Graphics2D g2d = (Graphics2D) g;
//        setUpBorders(g2d);
//        paintPaddle(g2d);
//        paintBall(g2d);
//
//        if(gameOver == true) {
//            Font custom = new Font("Dialog", Font.BOLD, 60);
//            g2d.setColor(Color.RED);
//            g2d.setFont(custom);
//            g2d.drawString("Game Over. Your score was: " + Ball.score + "!", 50, 500);
//
//        }
//
//    }
//
//
//
//
//    public void checkBorderHit() {
//        b.checkBorderHit();
//        p.checkBorderHit();
//
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        // TODO Auto-generated method stub
//        up = p.up;
//        down = p.down;
//        ballx =  b.ballx;
//        bally =  b.bally;
//        ballDirection = b.ballDirection;
//
//        b.moveBall();
//        checkBorderHit();
//        repaint();
//
//
//    }
//}