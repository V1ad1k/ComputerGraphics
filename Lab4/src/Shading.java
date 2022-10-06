import java.util.Random;

public class Shading {

    public static void main(String[] args) {

        int width  = 1000;
        int height = 1000;

        Triangle triangle = new Triangle(width, height, 0,0,0);
        Triangle triangle1 = new Triangle(width, height, 0,0,0);

        int[] point1 = { 500, 100 };
        int[] point4 = { 900, 100 };
        int[] point2 = { 100, 400 };
        int[] point3 = { 900, 900 };

//        --- Rainbow colors
        int[] color1a = { 255, 0 ,0};
        int[] color2a = { 0, 255, 0 };
        int[] color3a = { 0, 0, 255 };

//        int[] color1a = { 128, 128 ,128};
//        int[] color2a = { 128, 128, 128};
//        int[] color3a = { 128, 128, 128 };

        triangle.draw_triangle(point1, point2, point3, color1a, color2a, color3a);
        triangle.convert2background();
        triangle.write("img/triangle1.png");


        int[] color1 = { 128, 128 ,128};
        int[] color2 = { 255, 255, 255};
        int[] color3 = { 1, 1, 1 };

        triangle1.draw_triangle(point4, point2, point3, color1, color2,  color3);
        triangle1.convert2background();
        triangle1.write("img/triangle1.png");

        Random ran = new Random();
        int x = ran.nextInt(255);
        int y = ran.nextInt(255);

        int[] color1random = { 255, x ,y};
        int[] color2random = { x, 255, y };
        int[] color3random = { x, y, 255 };

        triangle.draw_triangle(point1, point2, point3, color1random, color2random,  color3random);
        triangle.convert2background();
        triangle.write("img/triangle1.jpg");

        //triangle.display_values1();



    }


}