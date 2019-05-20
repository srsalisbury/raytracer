package raytracer;

import static com.google.common.truth.Truth.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class CanvasTest {

  @Test
  public void checkCanvas() {
    Canvas c = new Canvas(10, 20);
    assertThat(c.width()).isEqualTo(10);
    assertThat(c.height()).isEqualTo(20);
    for (int y = 0; y < c.height(); y++) {
      for (int x = 0; x < c.width(); x++) {
        assertThat(c.pixelAt(x, y)).isEqualTo(Color.BLACK);
      }
    }
  }

  @Test
  public void writePixel() {
    Canvas c = new Canvas(10, 20);
    c.writePixel(2, 3, Color.RED);
    assertThat(c.pixelAt(2, 3)).isEqualTo(Color.RED);
  }

  @Test
  public void handlePixelOutOfRange() {
    Canvas c = new Canvas(10, 20);
    c.writePixel(-1, 0, Color.RED);
    assertThat(c.pixelAt(-1, 0)).isEqualTo(Color.BLACK);
    c.writePixel(0, -1, Color.RED);
    assertThat(c.pixelAt(0, -1)).isEqualTo(Color.BLACK);
    c.writePixel(11, 0, Color.RED);
    assertThat(c.pixelAt(11, 0)).isEqualTo(Color.BLACK);
    c.writePixel(0, 21, Color.RED);
    assertThat(c.pixelAt(0, 21)).isEqualTo(Color.BLACK);
  }
  /*Feature: Canvas

  Scenario: Constructing the PPM header
    Given c ← canvas(5, 3)
    When ppm ← canvas_to_ppm(c)
    Then lines 1-3 of ppm are
      """
      P3
      5 3
      255
      """

  Scenario: Constructing the PPM pixel data
    Given c ← canvas(5, 3)
      And c1 ← color(1.5, 0, 0)
      And c2 ← color(0, 0.5, 0)
      And c3 ← color(-0.5, 0, 1)
    When write_pixel(c, 0, 0, c1)
      And write_pixel(c, 2, 1, c2)
      And write_pixel(c, 4, 2, c3)
      And ppm ← canvas_to_ppm(c)
    Then lines 4-6 of ppm are
      """
      255 0 0 0 0 0 0 0 0 0 0 0 0 0 0
      0 0 0 0 0 0 0 128 0 0 0 0 0 0 0
      0 0 0 0 0 0 0 0 0 0 0 0 0 0 255
      """

  Scenario: Splitting long lines in PPM files
    Given c ← canvas(10, 2)
    When every pixel of c is set to color(1, 0.8, 0.6)
      And ppm ← canvas_to_ppm(c)
    Then lines 4-7 of ppm are
      """
      255 204 153 255 204 153 255 204 153 255 204 153 255 204 153 255 204
      153 255 204 153 255 204 153 255 204 153 255 204 153
      255 204 153 255 204 153 255 204 153 255 204 153 255 204 153 255 204
      153 255 204 153 255 204 153 255 204 153 255 204 153
      """

  Scenario: PPM files are terminated by a newline character
    Given c ← canvas(5, 3)
    When ppm ← canvas_to_ppm(c)
    Then ppm ends with a newline character
  */
}
