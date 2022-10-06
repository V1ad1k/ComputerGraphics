import java.awt.*;
import java.awt.geom.Area;

public class Triangle extends Bresenham {

    int left_point_x;
    int left_point_y;
    int[] left_point = new int[2];

    int left_color_r;
    int left_color_g;
    int left_color_b;

    int right_point_x;
    int right_point_y;
    int[] right_point = new int[2];

    int right_color_r;
    int right_color_g;
    int right_color_b;

    /*------------------------------------------------------------------------*/
    public Triangle(int width, int height, int red, int green, int blue) {
        super(width, height, red, green, blue);

    }

    /*------------------------------------------------------------------------*/
    public void draw_triangle(
            int[] point1, int[] point2, int[] point3
            , int[] color1, int[] color2, int[] color3)
    {

        bresenhamLine(point1, point2, color1[0], color1[1], color1[2], color2[0], color2[1], color2[2]);
        bresenhamLine(point1, point3, color1[0], color1[1], color1[2], color3[0], color3[1], color3[2]);
        bresenhamLine(point2, point3, color2[0], color2[1], color2[2], color3[0], color3[1], color3[2]);

        find_left_point();
    }

    /*------------------------------------------------------------------------*/
    public void display_values1() {

        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {

                /*---------------------------------------------*/
                if (image1[0][i][j] != -1) {
                    System.out.print(image1[0][i][j] + " ");
                } else {
                    System.out.print(" ");
                }
                /*---------------------------------------------*/
            }
            System.out.println();
        }
    }

    /*------------------------------------------------------------------------*/
    public void find_right_point(int y) {

        for (int x = width - 1; x > -1; x--) {

            if (image1[0][y][x] != -1) {

                right_point[1] = y;
                right_point[0] = x;

                right_color_r = image1[0][y][x];
                right_color_g = image1[1][y][x];
                right_color_b = image1[2][y][x];

                System.out.println("right point(y=" + y + " x=" + x + ")");
                break;

            }
        }
    }

    /*------------------------------------------------------------------------*/
    public void find_left_point() {

        for (int y = 0; y < height; y++) {

            for (int x = 0; x < width; x++) {

                if (image1[0][y][x] != -1) {

                    left_point[1] = y;
                    left_point[0] = x;

                    left_color_r = image1[0][y][x];
                    left_color_g = image1[1][y][x];
                    left_color_b = image1[2][y][x];

                    System.out.println("left point(y=" + y + " x=" + x + ")");
                    find_right_point(y);
                    System.out.print("Color " + left_color_r + "." + left_color_g + "." + left_color_b);
                    System.out.println(" " + right_color_r + "." + right_color_g + "." + right_color_b + "\n");

                    bresenhamLine(left_point, right_point
                            , left_color_r
                            , left_color_g
                            , left_color_b
                            , right_color_r
                            , right_color_g
                            , right_color_b);
                    break;
                }
            }
        }
    }
    /*------------------------------------------------------------------------*/
}