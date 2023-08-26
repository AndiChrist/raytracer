package io.github.andichrist.gfx;

import io.github.andichrist.Canvas;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import javafx.scene.paint.Color;

public class PPM {

  public static void write(Canvas image, String filename) throws IOException {
    BufferedWriter writer = new BufferedWriter(
        new OutputStreamWriter(new FileOutputStream(filename)));
    // write header
    int width = image.getWidth();
    int height = image.getHeight();
    writer.write("P3");
    writer.newLine();
    writer.write( width + " " + height);
    writer.newLine();
    writer.write("255");
    writer.newLine();

    // write graphics data
    for(int row = 0; row < height; row++) {
      for(int column = 0; column < width; column++) {
        Color color = image.getColor(column, row);

        writer.write((int) Math.round(color.getRed() * 255.0) + " ");
        writer.write((int) Math.round(color.getGreen() * 255.0) + " ");
        writer.write((int) Math.round(color.getBlue() * 255.0) + " ");

        if(column < width - 1)
          writer.write(" ");
      }
      writer.newLine();
    }
    writer.newLine();

    writer.flush();
    writer.close();
  }

  public static Canvas read(String filename) throws IOException {
    List<String> result = readPPM(filename);

    final int[] dimension = Arrays.stream(result.get(1).split(" ")).mapToInt(Integer::parseInt).toArray();
    Canvas image = new Canvas(
        dimension[0],
        dimension[1]
    );

    int row = 0;
    for(int i = 3; i < result.size()-1; i++) {
      final String[] pixels = result.get(i).split("  ");

      int col = 0;
      for (String pixel : pixels) {
        final int[] rgb = Arrays.stream(pixel.split(" ")).mapToInt(Integer::parseInt).toArray();

        Color color = new Color(rgb[0]/255, rgb[1]/255, rgb[2]/255, 1.0);

        image.setColor(col++, row, color);
      }
      row++;
    }

    return image;
  }

  public static List<String> readPPM(String filename) throws IOException {
    return Files.readAllLines(Paths.get(filename));
  }
}
