
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class ShapeSelection {
    public static final int LENGTH = 1000;
    private final JScrollPane sPane;

    public ShapeSelection(File dir, boolean isRC) {

        JPanel jPanel = new JPanel();
        jPanel.setBackground(Color.WHITE);
        jPanel.setLayout(new FlowLayout());
        sPane = new JScrollPane(jPanel);
        sPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jPanel.setPreferredSize(new Dimension(LENGTH , 0));
        File[] images = dir.listFiles((direction, name) -> name.endsWith(".jpg"));
        for (File image : images) {
            addShapeButtons(jPanel, image, isRC);
        }
    }

    private void addShapeButtons(JPanel panel, File image, boolean isRC) {

        JButton btn = new JButton();
        btn.setBorder(BorderFactory.createEmptyBorder());
        btn.setBackground(Color.WHITE);
        btn.setPreferredSize(new Dimension(LENGTH, LENGTH - 20));
        DrawIcon drawIcon =  new DrawIcon(image.getAbsolutePath());
        btn.setIcon(drawIcon);
        btn.setContentAreaFilled(false);
        btn.setTransferHandler(new TransferHandler("icon"));
        panel.add(btn);
    }

    public JComponent getComponent() {
        return sPane;
    }
    }

