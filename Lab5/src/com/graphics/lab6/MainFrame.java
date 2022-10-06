package com.graphics.lab6;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class MainFrame extends JFrame {


    private static final File IMAGES_DIR = new File("./images");
    private static final File SHAPES_DIR = new File("./shapes");

    private DrawArea drawArea = new DrawArea();

    public MainFrame() {
        setBounds(100, 100, 800, 600);
        setBackground(Color.white);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel chooserPanel = new JPanel();
        chooserPanel.setLayout(new GridLayout(2, 1));

        ShapeChooser imageChooser = new ShapeChooser(IMAGES_DIR, false);
        chooserPanel.add(imageChooser.getComponent());

        ShapeChooser shapeChooser = new ShapeChooser(SHAPES_DIR, true);
        chooserPanel.add(shapeChooser.getComponent());

        add(chooserPanel, BorderLayout.WEST);

        add(drawArea, BorderLayout.CENTER);
        add(drawArea.getPrecisionComponent(), BorderLayout.SOUTH);
        createMenu();

    }

    private void createMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem save = new JMenuItem("Save");
        file.add(save);
        save.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser(".");
            fileChooser.showSaveDialog(MainFrame.this);
            File file1 = fileChooser.getSelectedFile();
            if (file1 != null) {
                try (FileOutputStream fout = new FileOutputStream(file1 + ".ser")) {
                    ObjectOutputStream oos = new ObjectOutputStream(fout);
                    oos.writeObject(drawArea.getShapes());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        JMenuItem load = new JMenuItem("Load");
        file.add(load);
        load.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser(".");
            fileChooser.setFileFilter(new FileFilter() {
                @Override
                public boolean accept(File f) {
                    return f.isDirectory() || f.getAbsolutePath().endsWith(".ser");
                }

                @Override
                public String getDescription() {
                    return ".ser";
                }
            });
            fileChooser.showOpenDialog(MainFrame.this);
            File file1 = fileChooser.getSelectedFile();
            if (file1 != null) {
                try (FileInputStream in = new FileInputStream(file1)) {
                    ObjectInputStream ois = new ObjectInputStream(in);
                    drawArea.setShapes((java.util.List<DrawBase>) ois.readObject());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        });
        JMenuItem raster = new JMenuItem("Save Raster");
        file.add(raster);
        raster.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser(".");
            fileChooser.showSaveDialog(MainFrame.this);

            File file1 = fileChooser.getSelectedFile();
            if (file1 != null) {
                BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
                Graphics2D graphics = image.createGraphics();
                drawArea.paintComponent(graphics);
                graphics.dispose();
                try (FileOutputStream out = new FileOutputStream(file1 + ".png")) {
                    ImageIO.write(image, "png", out);
                    JOptionPane.showMessageDialog(MainFrame.this, "Image saved successfully to [" + file1.getAbsolutePath() + "]");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        JMenuItem clear = new JMenuItem("Clear");
        file.add(clear);
        clear.addActionListener(e -> {
            drawArea.clear();
        });

        menuBar.add(file);
        JMenu options = new JMenu("Options");
        JMenuItem color = new JMenuItem("Color");
        options.add(color);
        color.addActionListener(e -> {
            Color color1 = JColorChooser.showDialog(MainFrame.this, "Select a color", drawArea.getColor());
            drawArea.setColor(color1);
        });
        menuBar.add(options);
        add(menuBar, BorderLayout.NORTH);
    }

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
    }
}
