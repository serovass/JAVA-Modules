
package edu.school21.printer.logic;

import java.awt.*;
import java.awt.image.BufferedImage;



public class Logic {

    BufferedImage image;
    char whiteSymbol;
    char blackSymbol;

    public Logic(BufferedImage image, char whiteSymbol, char blackSymbol) {

        this.image = image;
        this.whiteSymbol = whiteSymbol;
        this.blackSymbol = blackSymbol;
    }

    public int[][] getBMPImageArray() {

        int[][] imageArray = new int[image.getWidth()][image.getHeight()];

        for (int x = 0; x < image.getWidth(); x++)
        {
            for (int y = 0; y < image.getHeight(); y++)
            {
                int color = image.getRGB(x, y);
                if (color== Color.BLACK.getRGB()) {
                    imageArray[x][y] = this.blackSymbol;
                } else if (color == Color.WHITE.getRGB()) {
                    imageArray[x][y] = this.whiteSymbol;
                }
            }
        }
        return imageArray;
    }

}
