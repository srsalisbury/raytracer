package raytracer;

import static com.google.common.truth.Truth.assertThat;

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
}

/*Feature: Matrices

Scenario: Calculating a cofactor of a 3x3 matrix
  Given the following 3x3 matrix A:
      |  3 |  5 |  0 |
      |  2 | -1 | -7 |
      |  6 | -1 |  5 |
  Then minor(A, 0, 0) = -12
    And cofactor(A, 0, 0) = -12
    And minor(A, 1, 0) = 25
    And cofactor(A, 1, 0) = -25

Scenario: Calculating the determinant of a 3x3 matrix
  Given the following 3x3 matrix A:
    |  1 |  2 |  6 |
    | -5 |  8 | -4 |
    |  2 |  6 |  4 |
  Then cofactor(A, 0, 0) = 56
    And cofactor(A, 0, 1) = 12
    And cofactor(A, 0, 2) = -46
    And determinant(A) = -196

Scenario: Calculating the determinant of a 4x4 matrix
  Given the following 4x4 matrix A:
    | -2 | -8 |  3 |  5 |
    | -3 |  1 |  7 |  3 |
    |  1 |  2 | -9 |  6 |
    | -6 |  7 |  7 | -9 |
  Then cofactor(A, 0, 0) = 690
    And cofactor(A, 0, 1) = 447
    And cofactor(A, 0, 2) = 210
    And cofactor(A, 0, 3) = 51
    And determinant(A) = -4071

Scenario: Testing an invertible matrix for invertibility
  Given the following 4x4 matrix A:
    |  6 |  4 |  4 |  4 |
    |  5 |  5 |  7 |  6 |
    |  4 | -9 |  3 | -7 |
    |  9 |  1 |  7 | -6 |
  Then determinant(A) = -2120
    And A is invertible

Scenario: Testing a noninvertible matrix for invertibility
  Given the following 4x4 matrix A:
    | -4 |  2 | -2 | -3 |
    |  9 |  6 |  2 |  6 |
    |  0 | -5 |  1 | -5 |
    |  0 |  0 |  0 |  0 |
  Then determinant(A) = 0
    And A is not invertible

Scenario: Calculating the inverse of a matrix
  Given the following 4x4 matrix A:
      | -5 |  2 |  6 | -8 |
      |  1 | -5 |  1 |  8 |
      |  7 |  7 | -6 | -7 |
      |  1 | -3 |  7 |  4 |
    And B ← inverse(A)
  Then determinant(A) = 532
    And cofactor(A, 2, 3) = -160
    And B[3,2] = -160/532
    And cofactor(A, 3, 2) = 105
    And B[2,3] = 105/532
    And B is the following 4x4 matrix:
      |  0.21805 |  0.45113 |  0.24060 | -0.04511 |
      | -0.80827 | -1.45677 | -0.44361 |  0.52068 |
      | -0.07895 | -0.22368 | -0.05263 |  0.19737 |
      | -0.52256 | -0.81391 | -0.30075 |  0.30639 |

Scenario: Calculating the inverse of another matrix
  Given the following 4x4 matrix A:
    |  8 | -5 |  9 |  2 |
    |  7 |  5 |  6 |  1 |
    | -6 |  0 |  9 |  6 |
    | -3 |  0 | -9 | -4 |
  Then inverse(A) is the following 4x4 matrix:
    | -0.15385 | -0.15385 | -0.28205 | -0.53846 |
    | -0.07692 |  0.12308 |  0.02564 |  0.03077 |
    |  0.35897 |  0.35897 |  0.43590 |  0.92308 |
    | -0.69231 | -0.69231 | -0.76923 | -1.92308 |

Scenario: Calculating the inverse of a third matrix
  Given the following 4x4 matrix A:
    |  9 |  3 |  0 |  9 |
    | -5 | -2 | -6 | -3 |
    | -4 |  9 |  6 |  4 |
    | -7 |  6 |  6 |  2 |
  Then inverse(A) is the following 4x4 matrix:
    | -0.04074 | -0.07778 |  0.14444 | -0.22222 |
    | -0.07778 |  0.03333 |  0.36667 | -0.33333 |
    | -0.02901 | -0.14630 | -0.10926 |  0.12963 |
    |  0.17778 |  0.06667 | -0.26667 |  0.33333 |

Scenario: Multiplying a product by its inverse
  Given the following 4x4 matrix A:
      |  3 | -9 |  7 |  3 |
      |  3 | -8 |  2 | -9 |
      | -4 |  4 |  4 |  1 |
      | -6 |  5 | -1 |  1 |
    And the following 4x4 matrix B:
      |  8 |  2 |  2 |  2 |
      |  3 | -1 |  7 |  0 |
      |  7 |  0 |  5 |  4 |
      |  6 | -2 |  0 |  5 |
    And C ← A * B
  Then C * inverse(B) = A
*/
