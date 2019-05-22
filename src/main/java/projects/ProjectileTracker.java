package projects;

import raytracer.Tuple;

public class ProjectileTracker {
  public static void main(String[] args) {
    Environment e = Environment.create(Tuple.vector(0, -0.1, 0), Tuple.vector(-0.08, 0, 0));
    Projectile p =
        Projectile.create(Tuple.point(0, 1, 0), Tuple.vector(1, 1, 0).normalize().times(10));
    while (p.position().y() > 0) {
      System.out.println(p.toString());
      p = p.tick(e);
    }
  }
}
