package raytracer;

import static com.google.common.truth.Truth.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class IntersectionsTest {

  @Test
  public void hitTestPositives() {
    Sphere s = new Sphere();
    Intersection i1 = Intersection.create(1.0, s);
    Intersection i2 = Intersection.create(2.0, s);
    Intersections xs = new Intersections();
    xs.add(i1);
    xs.add(i2);
    Intersection i = xs.hit();
    assertThat(i).isEqualTo(i1);
  }

  @Test
  public void hitTestOneNegative() {
    Sphere s = new Sphere();
    Intersection i1 = Intersection.create(-1.0, s);
    Intersection i2 = Intersection.create(1.0, s);
    Intersections xs = new Intersections();
    xs.add(i1);
    xs.add(i2);
    Intersection i = xs.hit();
    assertThat(i).isEqualTo(i2);
  }

  @Test
  public void hitTestNegatives() {
    Sphere s = new Sphere();
    Intersection i1 = Intersection.create(-2.0, s);
    Intersection i2 = Intersection.create(-1.0, s);
    Intersections xs = new Intersections();
    xs.add(i1);
    xs.add(i2);
    Intersection i = xs.hit();
    assertThat(i).isEqualTo(null);
  }

  @Test
  public void hitTestExtra() {
    Sphere s = new Sphere();
    Intersection i1 = Intersection.create(5.0, s);
    Intersection i2 = Intersection.create(7.0, s);
    Intersection i3 = Intersection.create(-3.0, s);
    Intersection i4 = Intersection.create(2.0, s);
    Intersections xs = new Intersections();
    xs.add(i1);
    xs.add(i2);
    xs.add(i3);
    xs.add(i4);
    Intersection i = xs.hit();
    assertThat(i).isEqualTo(i4);
  }
}
