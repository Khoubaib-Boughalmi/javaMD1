package ex00;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Logic {

    private final char whiteChar;
    private final char blackChar;
    private final String filePath;

    public Logic(char whiteChar, char blackChar, String filePath) {
        this.whiteChar = whiteChar;
        this.blackChar = blackChar;
        this.filePath = filePath;
    }

    public void printImage() {
        try {
            BufferedImage image = ImageIO.read(new File(filePath));
            System.out.println(image);
            for (int x = 0; x < image.getHeight(); x++) {
                for (int y = 0; y < image.getWidth(); y++) {
                    int color = image.getRGB(y, x);
                    if (color == Color.BLACK.getRGB()) {
                        System.out.print(blackChar);
                    } else if (color == Color.WHITE.getRGB()) {
                        System.out.print(whiteChar);
                    }
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void readBMP(String filePath) throws IOException {
        try (FileInputStream file = new FileInputStream(filePath)) {
            byte[] header = new byte[54];
            if (file.read(header) != 54) {
                throw new IOException("Invalid BMP file");
            }

            // Extract width, height, and bit depth
            int width = (header[18] & 0xFF) | ((header[19] & 0xFF) << 8);
            int height = (header[22] & 0xFF) | ((header[23] & 0xFF) << 8);
            int bitDepth = (header[28] & 0xFF);

            System.out.println("Width: " + width + ", Height: " + height + ", Bit Depth: " + bitDepth);

            // Move to pixel data offset
            int pixelDataOffset = (header[10] & 0xFF) | ((header[11] & 0xFF) << 8);
            file.skip(pixelDataOffset - 54);

            // Process based on bit depth
            if (bitDepth == 1) {
                process1BitBMP(file, width, height);
            } else if (bitDepth == 8) {
                process8BitBMP(file, width, height);
            } else {
                throw new IOException("Unsupported BMP format: only 1-bit and 8-bit supported");
            }
        }
    }

    private static void process1BitBMP(FileInputStream file, int width, int height) throws IOException {
      int rowSize = ((width + 7) / 8 + 3) & ~3; // Row size in bytes, padded to 4-byte boundary
      byte[] row = new byte[rowSize];
  
      // Create a buffer to store all rows in memory
      byte[][] pixelData = new byte[height][rowSize];
      for (int y = height - 1; y >= 0; y--) { // Read rows bottom-up into buffer
          if (file.read(pixelData[y]) != rowSize) {
              throw new IOException("Unexpected end of file");
          }
      }
  
      // Process rows top-down
      for (int y = 0; y < height; y++) {
          for (int x = 0; x < width; x++) {
              int byteIndex = x / 8;
              int bitIndex = 7 - (x % 8);
              boolean isWhite = (pixelData[y][byteIndex] & (1 << bitIndex)) != 0;
              System.out.printf("%s", isWhite ? "." : "O");
          }
          System.out.println();
      }
  }
  

    private static void process8BitBMP(FileInputStream file, int width, int height) throws IOException {
        int rowSize = (width + 3) & ~3; // Row size in bytes, padded to 4-byte boundary
        byte[] row = new byte[rowSize];

        for (int y = height - 1; y >= 0; y--) { // Bottom-up order
            if (file.read(row) != rowSize) {
                throw new IOException("Unexpected end of file");
            }

            for (int x = 0; x < width; x++) {
                int grayValue = row[x] & 0xFF; // Grayscale intensity (0–255)
                System.out.printf("Pixel (%d, %d): Gray=%d%n", x, y, grayValue);
            }
        }
    }

}
