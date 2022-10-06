
import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;


public class DrawIcon extends ImageIcon {


    public DrawIcon(String filename) {
        super(filename);
    }

    @Override
    public synchronized void paintIcon(Component c, Graphics g, int x, int y) {
        Image image = getImage();
        if (image == null) {
            return;
        }

        Insets insets = ((Container) c).getInsets();
        x = insets.left;
        y = insets.top;

        int width = c.getWidth() - x - insets.right;
        int height = c.getHeight() - y - insets.bottom;

        int imageWidth = image.getWidth(c);
        int imageHeight = image.getHeight(c);

        if (imageWidth * height < imageHeight * width) {
            imageWidth = (height * imageWidth) / imageHeight;
            x += (width - imageWidth) / 2;
            width = imageWidth;
        }
        else {
            imageHeight = (width * imageHeight) / imageWidth;
            y += (height - imageHeight) / 2;
            height = imageHeight;
        }

        ImageObserver imageObserver = getImageObserver();
        g.drawImage(image, x, y, width, height, imageObserver == null ? c : imageObserver);
    }


    @Override
    public int getIconWidth() {
        return 0;
    }


    @Override
    public int getIconHeight() {
        return 0;
    }
}