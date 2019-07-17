package raytracer;

import java.util.ArrayList;
import java.util.List;

public class Intersections {

  private List<Intersection> intersections;

  public Intersections() {
    this.intersections = new ArrayList<>();
  }

  public void add(Intersection i) {
    intersections.add(i);
  }

  public int count() {
    return intersections.size();
  }

  public Intersection get(int index) {
    return intersections.get(index);
  }

  public boolean isHit() {
    return hit() != null;
  }

  public Intersection hit() {
    Intersection bestHit = null;
    for (Intersection i : intersections) {
      if (i.t() < 0) {
        continue;
      }
      if ((bestHit == null) || (i.t() < bestHit.t())) {
        bestHit = i;
      }
    }
    return bestHit;
  }
}
