package raytracer;

import static com.google.common.truth.Truth.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class RayTest {

  @Test
  public void checkRay() {
    Tuple origin = Tuple.point(1, 2, 3);
    Tuple direction = Tuple.vector(4, 5, 6);
    Ray r = Ray.create(origin, direction);
    assertThat(r.origin()).isEqualTo(origin);
    assertThat(r.direction()).isEqualTo(direction);
  }

  @Test
  public void distanceAlongRay() {
    Ray r = Ray.create(Tuple.point(2, 3, 4), Tuple.vector(1, 0, 0));
    assertThat(r.position(0.0)).isEqualTo(Tuple.point(2, 3, 4));
    assertThat(r.position(1.0)).isEqualTo(Tuple.point(3, 3, 4));
    assertThat(r.position(-1.0)).isEqualTo(Tuple.point(1, 3, 4));
    assertThat(r.position(2.5)).isEqualTo(Tuple.point(4.5, 3, 4));
  }

  @Test
  public void translateRay() {
    Ray r = Ray.create(Tuple.point(1, 2, 3), Tuple.vector(0, 1, 0));
    Matrix m = Matrix.translate(3.0, 4.0, 5.0);
    Ray r2 = r.transform(m);
    assertThat(r2.origin()).isEqualTo(Tuple.point(4, 6, 8));
    assertThat(r2.direction()).isEqualTo(Tuple.vector(0, 1, 0));
  }

  @Test
  public void scaleRay() {
    Ray r = Ray.create(Tuple.point(1, 2, 3), Tuple.vector(0, 1, 0));
    Matrix m = Matrix.scale(2.0, 3.0, 4.0);
    Ray r2 = r.transform(m);
    assertThat(r2.origin()).isEqualTo(Tuple.point(2, 6, 12));
    assertThat(r2.direction()).isEqualTo(Tuple.vector(0, 3, 0));
  }
}
