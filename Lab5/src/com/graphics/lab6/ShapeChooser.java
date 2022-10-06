package com.graphics.lab6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class ShapeChooser {
    public static final int PREVIEW_SIZE = 70;
    private JScrollPane scrollPane;

    public ShapeChooser(File dir, boolean isShape) {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new FlowLayout());
        scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panel.setPreferredSize(new Dimension(PREVIEW_SIZE * 3 + 50, 0));
        File[] images = dir.listFiles((dir3, name) -> name.endsWith(".png") || name.endsWith(".jpg"));
        for (File image : images) {
            createPreview(panel, image, isShape);
        }
    }

    private void createPreview(JPanel panel, File image, boolean isShape) {
        JButton button = new JButton();
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setBackground(Color.WHITE);
        button.setPreferredSize(new Dimension(PREVIEW_SIZE, PREVIEW_SIZE));
        SelectionIcon imageIcon = isShape ? new ShapeIcon(image.getAbsolutePath()) : new SelectionIcon(image.getAbsolutePath());
        button.setIcon(imageIcon);
        button.setContentAreaFilled(false);
        button.addMouseListener(new DragMouseAdapter());
        button.setTransferHandler(new TransferHandler("icon"));
        panel.add(button);
    }

    public JComponent getComponent() {
        return scrollPane;
    }

    private class DragMouseAdapter extends MouseAdapter {

        public void mousePressed(MouseEvent e) {
            var c = (JComponent) e.getSource();
            var handler = c.getTransferHandler();
            handler.exportAsDrag(c, e, TransferHandler.COPY);
        }
    }

}
