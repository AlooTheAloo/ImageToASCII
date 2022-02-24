package com.company;

import java.io.File;
import java.io.FileWriter;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Main {

    public static void main(String[] args) throws IOException {
        Color[][] colorArray = createArray();
        String brightness = "_.,-=+:;cba!?0123456789$W#Ñ";
         CreateImage(colorArray, brightness);
    }

    public static void CreateImage(Color[][] colorArray, String brightness) throws IOException {

        String retval = "";
        FileWriter fw = new FileWriter("result.txt");

        for(int i = 0; i < colorArray[0].length; i++){
            for(int j = 0; j < colorArray.length; j++){
                Color col = colorArray[j][i];
                double targetBrightness = col.getRed() + col.getBlue() + col.getGreen();
                targetBrightness /= 3;

                double amt = 255f / brightness.length();

                retval += brightness.charAt((int) Math.floor(targetBrightness / amt));

            }
            retval += "\n";
        }
        fw.write(retval);
        fw.close();



    }





    public static Color[][] createArray() throws IOException {
        File file= new File("Image.png");
        BufferedImage img = ImageIO.read(file);

        Color[][] retVal =  new Color[img.getWidth() / 2][img.getHeight() / 4];

        for(int i = 0; i < img.getHeight() / 4; i++){
            for(int j = 0; j < img.getWidth() / 2; j++){
                retVal[j][i] = new Color(img.getRGB(j * 2, i * 4), false);
            }
        }

        return retVal;
    }

}
