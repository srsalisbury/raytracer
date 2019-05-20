package raytracer;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Canvas {

  private int width;
  private int height;
  private Color[] pixels;

  public Canvas(int width, int height) {
    this.width = width;
    this.height = height;
    pixels = new Color[width * height];
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        writePixel(x, y, Color.BLACK);
      }
    }
  }

  public int width() {
    return width;
  }

  public int height() {
    return height;
  }

  private boolean withinRange(int x, int y) {
    return x >= 0 && x < width && y >= 0 && y < height;
  }

  private int indexOf(int x, int y) {
    return y * width + x;
  }

  public Color pixelAt(int x, int y) {
    if (withinRange(x, y)) {
      return pixels[indexOf(x, y)];
    } else {
      return Color.BLACK;
    }
  }

  public void writePixel(int x, int y, Color color) {
    if (withinRange(x, y)) {
      pixels[indexOf(x, y)] = color;
    }
  }

  // Returns the ppm value for the given color channel value.
  private int toChannelByte(double channel) {
    if (channel <= 0.0) {
      return 0;
    }
    if (channel >= 1.0) {
      return 255;
    }
    return (int) (channel * 256.0);
  }

  // Writes the contents of this Canvas to the given raster image.
  private void writeToRaster(WritableRaster raster) {
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        Color c = pixelAt(x, y);
        int[] colorArray =
            new int[] {toChannelByte(c.r()), toChannelByte(c.g()), toChannelByte(c.b())};
        raster.setPixel(x, y, colorArray);
      }
    }
  }

  private RenderedImage toImage() {
    BufferedImage image = new BufferedImage(width(), height(), BufferedImage.TYPE_INT_RGB);
    writeToRaster(image.getRaster());
    return image;
  }

  // Writes this canvas to the given file in PNG format.
  public void writePngFile(File outFile) throws IOException {
    RenderedImage image = toImage();
    ImageIO.write(image, "png", outFile);
  }
}
