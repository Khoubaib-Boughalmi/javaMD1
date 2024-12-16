package ex00;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Logic{

    public static void printImage(char wc, char bc, String path) {
        try {
            BufferedImage image = ImageIO.read(new File(path));
            for (int x = 0; x < image.getHeight(); x++) {
                for (int y = 0; y < image.getWidth(); y++) {
                    int color = image.getRGB(y, x);
                    if (color == Color.BLACK.getRGB()) {
                        System.out.print(bc);
                    } else if (color == Color.WHITE.getRGB()) {
                        System.out.print(wc);
                    }
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
