package raytracer;

import static com.google.common.truth.Truth.assertThat;
import static raytracer.MatrixSubject.assertThat;
import static raytracer.TupleSubject.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class MatrixTest {

  @Test
  public void checkMatrix1() {
    Matrix m =
        new Matrix(
            4,
            4,
            new double[] {1, 2, 3, 4, 5.5, 6.5, 7.5, 8.5, 9, 10, 11, 12, 13.5, 14.5, 15.5, 16.5});
    assertThat(m.get(0, 0)).isEqualTo(1.0);
    assertThat(m.get(0, 3)).isEqualTo(4.0);
    assertThat(m.get(1, 0)).isEqualTo(5.5);
    assertThat(m.get(1, 2)).isEqualTo(7.5);
    assertThat(m.get(2, 2)).isEqualTo(11.0);
    assertThat(m.get(3, 0)).isEqualTo(13.5);
    assertThat(m.get(3, 2)).isEqualTo(15.5);
  }

  @Test
  public void checkMatrix2() {
    Matrix m = new Matrix(2, 2, new double[] {-3, 5, 1, -2});
    assertThat(m.get(0, 0)).isEqualTo(-3.0);
    assertThat(m.get(0, 1)).isEqualTo(5.0);
    assertThat(m.get(1, 0)).isEqualTo(1.0);
    assertThat(m.get(1, 1)).isEqualTo(-2.0);
  }

  @Test
  public void checkMatrix3() {
    Matrix m = new Matrix(3, 3, new double[] {-3, 5, 0, 1, -2, -7, 0, 1, 1});
    assertThat(m.get(0, 0)).isEqualTo(-3.0);
    assertThat(m.get(1, 1)).isEqualTo(-2.0);
    assertThat(m.get(2, 2)).isEqualTo(1.0);
  }

  @Test
  public void isMatrixEqual() {
    Matrix a = new Matrix(4, 4, new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 8, 7, 6, 5, 4, 3, 2});
    Matrix b = new Matrix(4, 4, new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 8, 7, 6, 5, 4, 3, 2});
    Matrix c = new Matrix(4, 4, new double[] {2, 3, 4, 5, 6, 7, 8, 9, 8, 7, 6, 5, 4, 3, 2, 1});
    assertThat(a).isEqualTo(b);
    assertThat(a).isNotEqualTo(c);
  }

  @Test
  public void timesMatrixSquare() {
    Matrix a = new Matrix(4, 4, new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 8, 7, 6, 5, 4, 3, 2});
    Matrix b = new Matrix(4, 4, new double[] {-2, 1, 2, 3, 3, 2, 1, -1, 4, 3, 6, 5, 1, 2, 7, 8});
    Matrix c =
        new Matrix(
            4,
            4,
            new double[] {20, 22, 50, 48, 44, 54, 114, 108, 40, 58, 110, 102, 16, 26, 46, 42});
    assertThat(a.times(b)).isEqualTo(c);
  }

  @Test
  public void timesMatrixRectangle() {
    Matrix a = new Matrix(1, 3, new double[] {1, 1, 1});
    Matrix b = new Matrix(3, 1, new double[] {1, 1, 1});
    Matrix c = new Matrix(1, 1, new double[] {3});
    assertThat(a.times(b)).isEqualTo(c);
  }

  @Test
  public void timesMatrixTuple() {
    Matrix a = new Matrix(4, 4, new double[] {1, 2, 3, 4, 2, 4, 4, 2, 8, 6, 4, 1, 0, 0, 0, 1});
    Tuple b = Tuple.create(1.0, 2.0, 3.0, 1.0);
    assertThat(a.times(b)).isEqualTo(Tuple.create(18.0, 24.0, 33.0, 1.0));
  }

  @Test
  public void timesMatrixIdentity() {
    Matrix a = new Matrix(4, 4, new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 8, 7, 6, 5, 4, 3, 2});
    assertThat(a.times(Matrix.identity())).isEqualTo(a);
  }

  @Test
  public void timesTupleIdentity() {
    Tuple a = Tuple.create(1.0, 2.0, 3.0, 4.0);
    assertThat(Matrix.identity().times(a)).isEqualTo(a);
  }

  @Test
  public void transposeMatrix() {
    Matrix a = new Matrix(4, 4, new double[] {0, 9, 3, 0, 9, 8, 0, 8, 1, 8, 5, 3, 0, 0, 5, 8});
    Matrix b = new Matrix(4, 4, new double[] {0, 9, 1, 0, 9, 8, 8, 0, 3, 0, 5, 5, 0, 8, 3, 8});
    assertThat(a.transpose()).isEqualTo(b);
  }

  @Test
  public void transposeIdentity() {
    assertThat(Matrix.identity().transpose()).isEqualTo(Matrix.identity());
  }

  @Test
  public void determinantTwoByTwo() {
    Matrix a = new Matrix(2, 2, new double[] {1, 5, -3, 2});
    assertThat(a.determinant()).isEqualTo(17.0);
  }

  @Test
  public void submatrixThreeByThree() {
    Matrix a = new Matrix(3, 3, new double[] {1, 5, 0, -3, 2, 7, 0, 6, -3});
    Matrix b = new Matrix(2, 2, new double[] {-3, 2, 0, 6});
    assertThat(a.submatrix(0, 2)).isEqualTo(b);
  }

  @Test
  public void submatrixFourByFour() {
    Matrix a = new Matrix(4, 4, new double[] {-6, 1, 1, 6, -8, 5, 8, 6, -1, 0, 8, 2, -7, 1, -1, 1});
    Matrix b = new Matrix(3, 3, new double[] {-6, 1, 6, -8, 8, 6, -7, -1, 1});
    assertThat(a.submatrix(2, 1)).isEqualTo(b);
  }

  @Test
  public void minorThreeByThree() {
    Matrix a = new Matrix(3, 3, new double[] {3, 5, 0, 2, -1, -7, 6, -1, 5});
    assertThat(a.minor(1, 0)).isEqualTo(25.0);
  }

  @Test
  public void cofactorThreeByThree() {
    Matrix a = new Matrix(3, 3, new double[] {3, 5, 0, 2, -1, -7, 6, -1, 5});
    assertThat(a.minor(0, 0)).isEqualTo(-12.0);
    assertThat(a.cofactor(0, 0)).isEqualTo(-12.0);
    assertThat(a.minor(1, 0)).isEqualTo(25.0);
    assertThat(a.cofactor(1, 0)).isEqualTo(-25.0);
  }

  @Test
  public void determinantThreeByThree() {
    Matrix a = new Matrix(3, 3, new double[] {1, 2, 6, -5, 8, -4, 2, 6, 4});
    assertThat(a.determinant()).isEqualTo(-196.0);
  }

  @Test
  public void determinantFourByFour() {
    Matrix a =
        new Matrix(4, 4, new double[] {-2, -8, 3, 5, -3, 1, 7, 3, 1, 2, -9, 6, -6, 7, 7, -9});
    assertThat(a.determinant()).isEqualTo(-4071.0);
  }

  @Test
  public void isInvertible() {
    Matrix a = new Matrix(4, 4, new double[] {6, 4, 4, 4, 5, 5, 7, 6, 4, -9, 3, -7, 9, 1, 7, -6});
    Matrix b = new Matrix(4, 4, new double[] {-4, 2, -2, -3, 9, 6, 2, 6, 0, -5, 1, -5, 0, 0, 0, 0});
    assertThat(a.isInvertible()).isEqualTo(true);
    assertThat(b.isInvertible()).isEqualTo(false);
  }

  @Test
  public void invert() {
    Matrix a =
        new Matrix(4, 4, new double[] {-5, 2, 6, -8, 1, -5, 1, 8, 7, 7, -6, -7, 1, -3, 7, 4});
    Matrix b =
        new Matrix(
            4,
            4,
            new double[] {
              0.21805, 0.45113, 0.24060, -0.04511, -0.80827, -1.45677, -0.44361, 0.52068, -0.07895,
              -0.22368, -0.05263, 0.19737, -0.52256, -0.81391, -0.30075, 0.30639
            });
    assertThat(a.invert()).isApproximatelyEqualTo(b);
  }

  @Test
  public void multiplyByInverse() {
    Matrix a =
        new Matrix(4, 4, new double[] {3, -9, 7, 3, 3, -8, 2, -9, -4, 4, 4, 1, -6, 5, -1, 1});
    assertThat(a.times(a.invert())).isApproximatelyEqualTo(Matrix.identity());
  }

  @Test
  public void translatePoint() {
    Tuple p = Tuple.point(-3, 4, 5);
    Matrix t = Matrix.translate(5, -3, 2);
    assertThat(t.times(p)).isEqualTo(Tuple.point(2, 1, 7));
  }

  @Test
  public void inverseTranslatePoint() {
    Matrix t = Matrix.translate(5, -3, 2);
    Matrix i = t.invert();
    Tuple p = Tuple.point(-3, 4, 5);
    assertThat(i.times(p)).isEqualTo(Tuple.point(-8, 7, 3));
  }

  @Test
  public void translateVector() {
    Matrix t = Matrix.translate(5, -3, 2);
    Tuple v = Tuple.vector(-3, 4, 5);
    assertThat(t.times(v)).isEqualTo(v);
  }

  @Test
  public void scalePoint() {
    Matrix t = Matrix.scale(2, 3, 4);
    Tuple p = Tuple.point(-4, 6, 8);
    assertThat(t.times(p)).isEqualTo(Tuple.point(-8, 18, 32));
  }

  @Test
  public void scaleVector() {
    Matrix t = Matrix.scale(2, 3, 4);
    Tuple v = Tuple.vector(-4, 6, 8);
    assertThat(t.times(v)).isEqualTo(Tuple.vector(-8, 18, 32));
  }

  @Test
  public void inverseScaleVector() {
    Matrix t = Matrix.scale(2, 3, 4);
    Matrix i = t.invert();
    Tuple v = Tuple.vector(-4, 6, 8);
    assertThat(i.times(v)).isEqualTo(Tuple.vector(-2, 2, 2));
  }

  @Test
  public void reflectPoint() {
    Matrix t = Matrix.scale(-1, 1, 1);
    Tuple p = Tuple.point(2, 3, 4);
    assertThat(t.times(p)).isEqualTo(Tuple.point(-2, 3, 4));
  }

  @Test
  public void rotateX() {
    Matrix half_quarter = Matrix.rotateX(Math.PI / 4);
    Matrix full_quarter = Matrix.rotateX(Math.PI / 2);
    Tuple p = Tuple.point(0, 1, 0);
    assertThat(half_quarter.times(p))
        .isApproximatelyEqualTo(Tuple.point(0, Math.sqrt(2) / 2, Math.sqrt(2) / 2));
    assertThat(full_quarter.times(p)).isApproximatelyEqualTo(Tuple.point(0, 0, 1));
  }
}
/*Feature: Matrix Transformations

Scenario: The inverse of an x-rotation rotates in the opposite direction
  Given p ← point(0, 1, 0)
    And half_quarter ← rotation_x(π / 4)
    And inv ← inverse(half_quarter)
  Then inv * p = point(0, √2/2, -√2/2)

Scenario: Rotating a point around the y axis
  Given p ← point(0, 0, 1)
    And half_quarter ← rotation_y(π / 4)
    And full_quarter ← rotation_y(π / 2)
  Then half_quarter * p = point(√2/2, 0, √2/2)
    And full_quarter * p = point(1, 0, 0)

Scenario: Rotating a point around the z axis
  Given p ← point(0, 1, 0)
    And half_quarter ← rotation_z(π / 4)
    And full_quarter ← rotation_z(π / 2)
  Then half_quarter * p = point(-√2/2, √2/2, 0)
    And full_quarter * p = point(-1, 0, 0)

Scenario: A shearing transformation moves x in proportion to y
  Given transform ← shearing(1, 0, 0, 0, 0, 0)
    And p ← point(2, 3, 4)
  Then transform * p = point(5, 3, 4)

Scenario: A shearing transformation moves x in proportion to z
  Given transform ← shearing(0, 1, 0, 0, 0, 0)
    And p ← point(2, 3, 4)
  Then transform * p = point(6, 3, 4)

Scenario: A shearing transformation moves y in proportion to x
  Given transform ← shearing(0, 0, 1, 0, 0, 0)
    And p ← point(2, 3, 4)
  Then transform * p = point(2, 5, 4)

Scenario: A shearing transformation moves y in proportion to z
  Given transform ← shearing(0, 0, 0, 1, 0, 0)
    And p ← point(2, 3, 4)
  Then transform * p = point(2, 7, 4)

Scenario: A shearing transformation moves z in proportion to x
  Given transform ← shearing(0, 0, 0, 0, 1, 0)
    And p ← point(2, 3, 4)
  Then transform * p = point(2, 3, 6)

Scenario: A shearing transformation moves z in proportion to y
  Given transform ← shearing(0, 0, 0, 0, 0, 1)
    And p ← point(2, 3, 4)
  Then transform * p = point(2, 3, 7)

Scenario: Individual transformations are applied in sequence
  Given p ← point(1, 0, 1)
    And A ← rotation_x(π / 2)
    And B ← scaling(5, 5, 5)
    And C ← translation(10, 5, 7)
  # apply rotation first
  When p2 ← A * p
  Then p2 = point(1, -1, 0)
  # then apply scaling
  When p3 ← B * p2
  Then p3 = point(5, -5, 0)
  # then apply translation
  When p4 ← C * p3
  Then p4 = point(15, 0, 7)

Scenario: Chained transformations must be applied in reverse order
  Given p ← point(1, 0, 1)
    And A ← rotation_x(π / 2)
    And B ← scaling(5, 5, 5)
    And C ← translation(10, 5, 7)
  When T ← C * B * A
  Then T * p = point(15, 0, 7)

Scenario: The transformation matrix for the default orientation
  Given from ← point(0, 0, 0)
    And to ← point(0, 0, -1)
    And up ← vector(0, 1, 0)
  When t ← view_transform(from, to, up)
  Then t = identity_matrix

Scenario: A view transformation matrix looking in positive z direction
  Given from ← point(0, 0, 0)
    And to ← point(0, 0, 1)
    And up ← vector(0, 1, 0)
  When t ← view_transform(from, to, up)
  Then t = scaling(-1, 1, -1)

Scenario: The view transformation moves the world
  Given from ← point(0, 0, 8)
    And to ← point(0, 0, 0)
    And up ← vector(0, 1, 0)
  When t ← view_transform(from, to, up)
  Then t = translation(0, 0, -8)

Scenario: An arbitrary view transformation
  Given from ← point(1, 3, 2)
    And to ← point(4, -2, 8)
    And up ← vector(1, 1, 0)
  When t ← view_transform(from, to, up)
  Then t is the following 4x4 matrix:
      | -0.50709 | 0.50709 |  0.67612 | -2.36643 |
      |  0.76772 | 0.60609 |  0.12122 | -2.82843 |
      | -0.35857 | 0.59761 | -0.71714 |  0.00000 |
      |  0.00000 | 0.00000 |  0.00000 |  1.00000 |
*/
