package com.company;

import java.io.File;
import java.io.FileWriter;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;


public class Main {
    public static int detail = 2;

    public static void main(String[] args) throws IOException {
        System.out.print("Enter downscale level (lower is more detailed, 2 - 8 recommended) WARNING, LOW NUMBERS MAY CAUSE CLIPPING AND SLOWER PROCESSING TIME : ");

        Scanner scanner = new Scanner(System.in);
        detail = scanner.nextInt();
        Color[][] colorArray = createArray();
        String brightness = "_.,-=+:;cba!?0123456789$W#N";
        CreateImage(colorArray, brightness);
    }

    public static void CreateImage(Color[][] colorArray, String brightness) throws IOException {

        String retval = "";
        FileWriter fw = new FileWriter("result.txt");

        for(int i = 0; i < colorArray[0].length; i++){
            clearBoard();
            System.out.println((float) i * 100 / colorArray[0].length + "%");
            for(int j = 0; j < colorArray.length; j++){
                Color col = colorArray[j][i];
                double targetBrightness = col.getRed() + col.getBlue() + col.getGreen();
                targetBrightness /= 3;

                double amt = 255f / brightness.length();
                int numToGoAt = (int) Math.floor(targetBrightness / amt);
                if(numToGoAt >= brightness.length()){
                    numToGoAt = brightness.length() -1;
                }

                retval += brightness.charAt(numToGoAt);

            }
            retval += "\n";
        }
        fw.write(retval);
        fw.close();
    }

    private static void clearBoard(){
        for(int i = 0; i < 50; i++){
            System.out.println();
        }
    }




    public static Color[][] createArray() throws IOException {
        File file= new File("Image.png");
        BufferedImage img = ImageIO.read(file);

        Color[][] retVal =  new Color[img.getWidth() / detail][img.getHeight() / (detail * 2)];

        for(int i = 0; i < img.getHeight() / (detail * 2); i++){
            for(int j = 0; j < img.getWidth() / detail; j++){
                retVal[j][i] = new Color(img.getRGB(j * detail, i * (detail * 2)), false);
            }
        }

        return retVal;
    }

}
