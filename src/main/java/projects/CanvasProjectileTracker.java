package projects;

import java.io.File;
import java.io.IOException;
import raytracer.Canvas;
import raytracer.Color;
import raytracer.Tuple;

public class CanvasProjectileTracker {
  public static void main(String[] args) {
    Environment e = Environment.create(Tuple.vector(0, -0.2, 0), Tuple.vector(-0.08, 0, 0));
    Projectile p =
        Projectile.create(Tuple.point(0, 1, 0), Tuple.vector(1, 1, 0).normalize().times(10));
    Canvas c = new Canvas(400, 300);
    while (p.position().y() > 0) {
      c.writePixel((int) p.position().x(), c.height() - 1 - (int) p.position().y(), Color.WHITE);
      p = p.tick(e);
    }
    try {
      c.writePngFile(new File("PhysicsTest.png"));
    } catch (IOException ex) {
      System.err.println("Error Writing Image " + ex.getMessage());
    }
  }
}
