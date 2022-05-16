
package edu.school21.printer.app;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import edu.school21.printer.logic.Logic;


public class Application {

    public static void main(String[] args) throws IOException {

        if (args.length != 2) {

            System.out.println("Usage: java Application <whiteSymbol> <blackSymbol>");
            System.exit(-1);
        }

        BufferedImage image = ImageIO.read(Application.class.getResource("/resources/image.bmp"));

        Logic logic = new Logic(image, args[0].charAt(0), args[1].charAt(0));
        int[][] imageArray = logic.getBMPImageArray();

        for (int x = 0; x < imageArray.length; x++) {

            for (int y = 0; y < imageArray[x].length; y++) {

                System.out.print((char) imageArray[y][x]);
            }
            System.out.println();
        }
    }
}
