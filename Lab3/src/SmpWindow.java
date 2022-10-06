//
//import javax.swing.*;
//import java.awt.*;
//
//public class SmpWindow extends JFrame {
//    public static final int SZER = 800;
//    public static final int WYS = 500;
//    DrawWndPanel pane;
//    static int width = 1400;
//    static int height =900;
//
//    public SmpWindow() {
//        this.setSize(width, height);
//        Container contents = this.getContentPane();
//        this.pane = new DrawWndPanel();
//        contents.add(this.pane);
//    }
//
//    public static int getWidth1(){
//        return width;
//    }
//
//    public static int getHeight1(){
//        return height;
//    }
//
//    public void LoadImage(String name) {
//        this.pane.LoadImage(name);
//    }
//
//    public void SetMode(int mode) {
//        this.pane.SetMode(mode);
//    }
//}