
package edu.school21.printer.app;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import edu.school21.printer.logic.Logic;


public class Application {

    public static void main(String[] args) throws IOException {

        if (args.length != 3) {

            System.out.println("Usage: java Application <whiteSymbol> <blackSymbol> <image.bmp>");
            System.exit(-1);
        }

        String imagePath = args[2];
        BufferedImage image = null;
        try {
            image = ImageIO.read(new FileInputStream(imagePath));

        } catch (FileNotFoundException e) {
            System.err.println("No such file or directory.");
            System.exit(-1);
        }

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
