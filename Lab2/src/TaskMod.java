//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class Window extends JFrame implements ActionListener {
//    static int length = 1000;
//    static int height = 1000;
//    Display display = new Display();
//
//    Window() {
//
//        setTitle("Program Display");
//        setSize(length + 22, height + 40);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        JButton restart = new JButton("Start New Game");
//        add(display);
//        display.add(restart);
//        restart.addActionListener(this);
//        setVisible(true);
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent arg0) {
//        // TODO Auto-generated method stub
//        remove(display);
//        Display display2 = new Display();
//        JButton restart = new JButton("Start New Game");
//        add(display2);
//        display2.add(restart);
//        restart.addActionListener(this);
//        revalidate();
//        repaint();
//    }
//}
//
