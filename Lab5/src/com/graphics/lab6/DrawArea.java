package com.graphics.lab6;

import javax.swing.*;
import java.awt.*;
import java.awt.dnd.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Collections;

public class DrawArea extends JPanel implements DropTargetListener, MouseListener, MouseMotionListener {


    private java.util.List<DrawBase> shapes = new ArrayList();

    private DrawBase moveSelected;
    private DrawBase rotateSelected;
    private DrawBase resizeSelected;
    private JPanel precision;

    private Color color = Color.lightGray;

    public DrawArea() {
        DropTarget dropTarget = new DropTarget(this, DnDConstants.ACTION_COPY_OR_MOVE,
                this, true, null);
        this.setDropTarget(dropTarget);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    public JPanel getPrecisionComponent() {
        precision = new JPanel();
        JButton leftButton = new JButton("Left");
        leftButton.addActionListener(e -> {
            if (moveSelected != null) {
                moveSelected.pmove(-1, 0);
                getParent().repaint();
            }
        });
        precision.add(leftButton);
        JButton rightButton = new JButton("Right");
        rightButton.addActionListener(e -> {
            if (moveSelected != null) {
                moveSelected.pmove(1, 0);
                getParent().repaint();
            }
        });
        precision.add(rightButton);
        JButton topButton = new JButton("Top");
        topButton.addActionListener(e -> {
            if (moveSelected != null) {
                moveSelected.pmove(0, -1);
                getParent().repaint();
            }
        });
        precision.add(topButton);
        JButton bottomButton = new JButton("Bottom");
        bottomButton.addActionListener(e -> {
            if (moveSelected != null) {
                moveSelected.pmove(0, 1);
                getParent().repaint();
            }
        });
        precision.add(bottomButton);
        JButton rotateRightButton = new JButton("Rotate Left");
        rotateRightButton.addActionListener(e -> {
            if (moveSelected != null) {
                moveSelected.protate(-1);
                getParent().repaint();
            }
        });
        precision.add(rotateRightButton);
        JButton rotateLeftButton = new JButton("Rotate Right");
        precision.add(rotateLeftButton);
        rotateLeftButton.addActionListener(e -> {
            if (moveSelected != null) {
                moveSelected.protate(1);
                getParent().repaint();
            }
        });
        precision.setVisible(false);
        return precision;
    }

    @Override
    public void dragEnter(DropTargetDragEvent dtde) {

    }

    @Override
    public void dragOver(DropTargetDragEvent dtde) {

    }

    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {

    }

    @Override
    public void dragExit(DropTargetEvent dte) {

    }

    @Override
    public void drop(DropTargetDropEvent event) {
        try {
            SelectionIcon icon = (SelectionIcon) event.getTransferable().getTransferData(event.getTransferable().getTransferDataFlavors()[0]);
            Point center = event.getLocation();
            if (icon instanceof ShapeIcon) {
                ShapeIcon shapeIcon = (ShapeIcon) icon;
                if (shapeIcon.getShapeType() == ShapeType.RECTANGLE) {
                    DrawShape drawShape = new DrawRect(center, color);
                    shapes.add(drawShape);
                }
                if (shapeIcon.getShapeType() == ShapeType.CIRCLE) {
                    DrawShape drawShape = new DrawCircle(center, color);
                    shapes.add(drawShape);
                }

            } else {
                DrawImage drawImage = new DrawImage(center, icon.getImage());
                shapes.add(drawImage);
            }
            event.dropComplete(true);
            moveSelected = null;
            precision.setVisible(false);
            getParent().repaint();
        } catch (Exception e) {
            e.printStackTrace();
            event.dropComplete(false);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        for (DrawBase shape : shapes) {
            shape.draw(g2d);
            /*
            int radius = 5;
            g2d.setColor(Color.black);
            g2d.fillOval((int) (shape.getCenter().getX() - radius), (int) (shape.getCenter().getY() - radius), 2 * radius, 2 * radius);
            g2d.setColor(Color.RED);
            g2d.fillOval((int) (shape.getResizePoint().getX() - radius), (int) (shape.getResizePoint().getY() - radius), 2 * radius, 2 * radius);
            g2d.setColor(Color.GREEN);
            g2d.fillOval((int) (shape.getRotationPoint().getX() - radius), (int) (shape.getRotationPoint().getY() - radius), 2 * radius, 2 * radius);
             */
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        ArrayList<DrawShape> reversedShapes = new ArrayList(shapes);
        Collections.reverse(reversedShapes);
        for (DrawBase shape : reversedShapes) {
            if (shape.isCenterSelected(e.getX(), e.getY())) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    clearSelection();
                    moveSelected = shape;
                    precision.setVisible(true);
                    return;
                }
                if (SwingUtilities.isRightMouseButton(e)) {
                    JPopupMenu menu = new JPopupMenu();

                    JMenuItem bringToFront = new JMenuItem("Bring To Front");
                    menu.add(bringToFront);
                    bringToFront.addActionListener(e1 -> {

                        shape.BringTop();
                        getParent().repaint();
                    });

                    JMenuItem sendToBack = new JMenuItem("Push To Bottom");
                    menu.add(sendToBack);
                    sendToBack.addActionListener(e1 -> {
                        Collections.swap(shapes, 0, shapes.indexOf(shape));
                        getParent().repaint();

                    });

                    JMenuItem deleteElement = new JMenuItem("Delete");
                    menu.add(deleteElement);
                    deleteElement.addActionListener(e1 -> {
                        clearSelection();
                        shapes.remove(shape);
                        getParent().repaint();
                    });
                    menu.show(this, e.getX(), e.getY());
                }
                return;
            }
            if (shape.isRotationSelected(e.getX(), e.getY())) {
                clearSelection();
                rotateSelected = shape;
                return;
            }
            if (shape.isResizeSelected(e.getX(), e.getY())) {
                clearSelection();
                resizeSelected = shape;
                return;
            }
        }
        clearSelection();
    }

    private void clearSelection() {
        moveSelected = null;
        resizeSelected = null;
        resizeSelected = null;
        precision.setVisible(false);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        rotateSelected = null;
        resizeSelected = null;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (moveSelected != null) {
            moveSelected.move(e.getX(), e.getY());
            getParent().repaint();
        }
        if (rotateSelected != null) {
            rotateSelected.rotate(e.getX(), e.getY());
            getParent().repaint();
        }
        if (resizeSelected != null) {
            resizeSelected.resize(e.getX(), e.getY());
            getParent().repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public java.util.List<DrawBase> getShapes() {
        return shapes;
    }

    public void setShapes(java.util.List<DrawBase> shapes) {
        this.shapes = shapes;
        getParent().repaint();
    }

    public void clear() {
        this.shapes.clear();
        getParent().repaint();
    }
}
