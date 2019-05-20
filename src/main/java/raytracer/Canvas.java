package raytracer;

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
}
