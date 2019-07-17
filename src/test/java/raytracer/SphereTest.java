package raytracer;

import static com.google.common.truth.Truth.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class SphereTest {

  @Test
  public void intersectThrough() {
    Ray r = Ray.create(Tuple.point(-2, 0, 0), Tuple.vector(1, 0, 0));
    Sphere s = new Sphere();
    Intersections xs = s.intersect(r);
    assertThat(xs.count()).isEqualTo(2);
    assertThat(xs.get(0)).isEqualTo(Intersection.create(1.0, s));
    assertThat(xs.get(1)).isEqualTo(Intersection.create(3.0, s));
  }

  @Test
  public void intersectTangent() {
    Ray r = Ray.create(Tuple.point(-2, 0, 1), Tuple.vector(1, 0, 0));
    Sphere s = new Sphere();
    Intersections xs = s.intersect(r);
    assertThat(xs.count()).isEqualTo(2);
    assertThat(xs.get(0)).isEqualTo(Intersection.create(2.0, s));
    assertThat(xs.get(1)).isEqualTo(Intersection.create(2.0, s));
  }

  @Test
  public void intersectMiss() {
    Ray r = Ray.create(Tuple.point(-2, 0, 2), Tuple.vector(1, 0, 0));
    Sphere s = new Sphere();
    Intersections xs = s.intersect(r);
    assertThat(xs.count()).isEqualTo(0);
  }

  @Test
  public void getTransformTest() {
    Sphere s = new Sphere();
    assertThat(s.getTransform()).isEqualTo(Matrix.identity());
  }

  @Test
  public void setTransformTest() {
    Sphere s = new Sphere();
    Matrix t = Matrix.translate(2, 3, 4);
    s.setTransform(t);
    assertThat(s.getTransform()).isEqualTo(t);
  }

  @Test
  public void intersectTranslatedSphere() {
    Ray r = Ray.create(Tuple.point(0, 0, -5), Tuple.vector(0, 0, 1));
    Sphere s = new Sphere();
    Matrix t = Matrix.scale(2, 2, 2);
    s.setTransform(t);
    Intersections xs = s.intersect(r);
    assertThat(xs.count()).isEqualTo(2);
    assertThat(xs.get(0).t()).isEqualTo(3.0);
    assertThat(xs.get(1).t()).isEqualTo(7.0);
  }

  @Test
  public void intersectScaledSphere() {
    Ray r = Ray.create(Tuple.point(0, 0, -5), Tuple.vector(0, 0, 1));
    Sphere s = new Sphere();
    Matrix t = Matrix.translate(5, 0, 0);
    s.setTransform(t);
    Intersections xs = s.intersect(r);
    assertThat(xs.count()).isEqualTo(0);
  }
}
