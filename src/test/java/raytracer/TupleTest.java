package raytracer;

import static com.google.common.truth.Truth.assertThat;
import static raytracer.Testing.EPSILON;
import static raytracer.TupleSubject.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TupleTest {

  @Test
  public void checkPoint() {
    Tuple a = Tuple.create(4.3, -4.2, 3.1, 1.0);
    assertThat(a.x()).isEqualTo(4.3);
    assertThat(a.y()).isEqualTo(-4.2);
    assertThat(a.z()).isEqualTo(3.1);
    assertThat(a.w()).isEqualTo(1.0);
    assertThat(a.isPoint()).isTrue();
    assertThat(a.isVector()).isFalse();
  }

  @Test
  public void checkVector() {
    Tuple a = Tuple.create(4.3, -4.2, 3.1, 0.0);
    assertThat(a.x()).isEqualTo(4.3);
    assertThat(a.y()).isEqualTo(-4.2);
    assertThat(a.z()).isEqualTo(3.1);
    assertThat(a.w()).isEqualTo(0.0);
    assertThat(a.isPoint()).isFalse();
    assertThat(a.isVector()).isTrue();
  }

  @Test
  public void pointContructor() {
    Tuple p = Tuple.point(4, -4, 3);
    assertThat(p).isEqualTo(Tuple.create(4.0, -4.0, 3.0, 1.0));
  }

  @Test
  public void vectorContructor() {
    Tuple v = Tuple.vector(4, -4, 3);
    assertThat(v).isEqualTo(Tuple.create(4.0, -4.0, 3.0, 0.0));
  }

  @Test
  public void addTuple() {
    Tuple a1 = Tuple.create(3, -2, 5, 1);
    Tuple a2 = Tuple.create(-2, 3, 1, 0);
    assertThat(a1.add(a2)).isEqualTo(Tuple.create(1, 1, 6, 1));
  }

  @Test
  public void subtractPoint() {
    Tuple p1 = Tuple.point(3, 2, 1);
    Tuple p2 = Tuple.point(5, 6, 7);
    assertThat(p1.subtract(p2)).isEqualTo(Tuple.vector(-2, -4, -6));
  }

  @Test
  public void subVectorFromPoint() {
    Tuple p1 = Tuple.point(3, 2, 1);
    Tuple v2 = Tuple.vector(5, 6, 7);
    assertThat(p1.subtract(v2)).isEqualTo(Tuple.point(-2, -4, -6));
  }

  @Test
  public void subtractVector() {
    Tuple v1 = Tuple.vector(3, 2, 1);
    Tuple v2 = Tuple.vector(5, 6, 7);
    assertThat(v1.subtract(v2)).isEqualTo(Tuple.vector(-2, -4, -6));
  }

  @Test
  public void subtVectorFrom0() {
    Tuple zero = Tuple.vector(0, 0, 0);
    Tuple v = Tuple.vector(1, -2, 3);
    assertThat(zero.subtract(v)).isEqualTo(Tuple.vector(-1, 2, -3));
  }

  @Test
  public void timesTuple() {
    Tuple a = Tuple.create(1, -2, 3, -4);
    assertThat(a.times(3.5)).isEqualTo(Tuple.create(3.5, -7, 10.5, -14));
    assertThat(a.times(0.5)).isEqualTo(Tuple.create(0.5, -1, 1.5, -2));
  }

  @Test
  public void divideTuple() {
    Tuple a = Tuple.create(1, -2, 3, -4);
    assertThat(a.divide(2)).isEqualTo(Tuple.create(0.5, -1, 1.5, -2));
  }

  @Test
  public void magnitudeVector() {
    Tuple v1 = Tuple.vector(1, 0, 0);
    assertThat(v1.magnitude()).isEqualTo(1.0);

    Tuple v2 = Tuple.vector(0, 1, 0);
    assertThat(v2.magnitude()).isEqualTo(1.0);

    Tuple v3 = Tuple.vector(0, 0, 1);
    assertThat(v3.magnitude()).isEqualTo(1.0);

    Tuple v4 = Tuple.vector(1, 2, 3);
    assertThat(v4.magnitude()).isEqualTo(Math.sqrt(14));

    Tuple v5 = Tuple.vector(-1, -2, -3);
    assertThat(v5.magnitude()).isEqualTo(Math.sqrt(14));
  }

  @Test
  public void normalizeVector() {
    Tuple v1 = Tuple.vector(4, 0, 0);
    assertThat(v1.normalize()).isEqualTo(Tuple.vector(1, 0, 0));

    Tuple v2 = Tuple.vector(1, 2, 3);
    assertThat(v2.normalize()).isApproximatelyEqualTo(Tuple.vector(0.26726, 0.53452, 0.80178));
  }

  @Test
  public void magnitudeNormalizeVector() {
    Tuple v = Tuple.vector(1, 2, 3);
    Tuple norm = v.normalize();
    assertThat(norm.magnitude()).isWithin(EPSILON).of(1);
  }

  @Test
  public void dotProductVector() {
    Tuple a = Tuple.vector(1, 2, 3);
    Tuple b = Tuple.vector(2, 3, 4);
    assertThat(a.dot(b)).isWithin(EPSILON).of(20);
  }

  @Test
  public void crossProductVector() {
    Tuple a = Tuple.vector(1, 2, 3);
    Tuple b = Tuple.vector(2, 3, 4);
    assertThat(a.cross(b)).isEqualTo(Tuple.vector(-1, 2, -1));
    assertThat(b.cross(a)).isEqualTo(Tuple.vector(1, -2, 1));
  }
}
/*Feature: Tuples, Vectors, and Points

Scenario: Reflecting a vector approaching at 45°
  Given v ← vector(1, -1, 0)
    And n ← vector(0, 1, 0)
  When r ← reflect(v, n)
  Then r = vector(1, 1, 0)

Scenario: Reflecting a vector off a slanted surface
  Given v ← vector(0, -1, 0)
    And n ← vector(√2/2, √2/2, 0)
  When r ← reflect(v, n)
  Then r = vector(1, 0, 0)
*/
