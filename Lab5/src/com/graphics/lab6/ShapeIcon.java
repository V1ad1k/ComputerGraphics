package com.graphics.lab6;

public class ShapeIcon extends SelectionIcon {


    private ShapeType shapeType;

    public ShapeIcon(String filename) {
        super(filename);
        if (filename.contains("rectangle")) {
            shapeType = ShapeType.RECTANGLE;
        }
        if (filename.contains("circle")) {
            shapeType = ShapeType.CIRCLE;
        }
    }

    public ShapeType getShapeType() {
        return shapeType;
    }
}
