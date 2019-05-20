package raytracer;

import static com.google.common.truth.Truth.assertThat;
import static raytracer.ColorSubject.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ColorTest {

  @Test
  public void checkColor() {
    Color c = Color.create(-0.5, 0.4, 1.7);
    assertThat(c.r()).isEqualTo(-0.5);
    assertThat(c.g()).isEqualTo(0.4);
    assertThat(c.b()).isEqualTo(1.7);
  }

  @Test
  public void addColor() {
    Color c1 = Color.create(0.9, 0.6, 0.75);
    Color c2 = Color.create(0.7, 0.1, 0.25);
    assertThat(c1.add(c2)).isApproximatelyEqualTo(Color.create(1.6, 0.7, 1.0));
  }

  @Test
  public void subtractColor() {
    Color c1 = Color.create(0.9, 0.6, 0.75);
    Color c2 = Color.create(0.7, 0.1, 0.25);
    assertThat(c1.subtract(c2)).isApproximatelyEqualTo(Color.create(0.2, 0.5, 0.5));
  }

  @Test
  public void valueColor() {
    Color c = Color.create(0.2, 0.3, 0.4);
    assertThat(c.value(2)).isApproximatelyEqualTo(Color.create(0.4, 0.6, 0.8));
  }

  @Test
  public void timesColor() {
    Color c1 = Color.create(1, 0.2, 0.4);
    Color c2 = Color.create(0.9, 1, 0.1);
    assertThat(c1.times(c2)).isApproximatelyEqualTo(Color.create(0.9, 0.2, 0.04));
  }
}
