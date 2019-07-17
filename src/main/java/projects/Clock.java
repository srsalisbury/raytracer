package projects;

import java.io.File;
import java.io.IOException;
import raytracer.Canvas;
import raytracer.Color;
import raytracer.Matrix;
import raytracer.Tuple;

public class Clock {
  public static void main(String[] args) {
    Canvas c = new Canvas(200, 200);
    Tuple p = Tuple.point(0, 1, 0);
    Matrix rotate = Matrix.rotateZ(Math.PI / 6);
    for (int i = 0; i < 12; i++) {
      c.writePixel((int) (p.x() * 75 + 100), (int) (p.y() * 75 + 100), Color.WHITE);
      p = rotate.times(p);
    }
    try {
      c.writePngFile(new File("ClockTest.png"));
    } catch (IOException ex) {
      System.err.println("Error Writing Image " + ex.getMessage());
    }
  }
}
