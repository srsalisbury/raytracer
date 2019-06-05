package raytracer;

import java.util.Arrays;

public class Matrix {

  private int numCols;
  private int numRows;
  private double[] values;

  public Matrix(int numRows, int numCols) {
    this.numCols = numCols;
    this.numRows = numRows;
    values = new double[numCols * numRows];
  }

  public Matrix(int numRows, int numCols, double[] values) {
    this.numCols = numCols;
    this.numRows = numRows;
    this.values = new double[numCols * numRows];
    for (int i = 0; i < values.length; i++) {
      this.values[i] = values[i];
    }
  }

  public static Matrix identity() {
    return new Matrix(4, 4, new double[] {1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1});
  }

  public int numCols() {
    return numCols;
  }

  public int numRows() {
    return numRows;
  }

  private boolean withinRange(int row, int column) {
    return row >= 0 && row < numRows && column >= 0 && column < numCols;
  }

  private int indexOf(int row, int column) {
    return row * numCols + column;
  }

  public double get(int row, int column) {
    if (withinRange(row, column)) {
      return values[indexOf(row, column)];
    } else {
      return 0.0;
    }
  }

  public void set(int row, int column, double value) {
    if (withinRange(row, column)) {
      values[indexOf(row, column)] = value;
    }
  }

  private static Matrix fromTuple(Tuple t) {
    Matrix m = new Matrix(4, 1);
    m.set(0, 0, t.x());
    m.set(1, 0, t.y());
    m.set(2, 0, t.z());
    m.set(3, 0, t.w());
    return m;
  }

  private Tuple toTuple() {
    Tuple t = Tuple.create(get(0, 0), get(1, 0), get(2, 0), get(3, 0));
    return t;
  }

  public Matrix times(Matrix m) {
    Matrix t = new Matrix(numRows(), m.numCols());
    for (int row = 0; row < numRows(); row++) {
      for (int col = 0; col < m.numCols(); col++) {
        double dot = 0.0;
        for (int i = 0; i < numCols(); i++) {
          dot += get(row, i) * m.get(i, col);
        }
        t.set(row, col, dot);
      }
    }
    return t;
  }

  public Tuple times(Tuple t) {
    return times(fromTuple(t)).toTuple();
  }

  public Matrix transpose() {
    Matrix m = new Matrix(numCols(), numRows());
    for (int row = 0; row < numRows(); row++) {
      for (int col = 0; col < m.numCols(); col++) {
        m.set(row, col, get(col, row));
      }
    }
    return m;
  }

  public double determinant() {
    if (numRows != numCols) {
      throw new IllegalArgumentException("Determinant requires square matrix to work");
    }
    if (numRows == 2) {
      return determinantTwoByTwo();
    }
    double d = 0.0;
    for (int col = 0; col < numCols; col++) {
      d += get(0, col) * cofactor(0, col);
      System.out.println(d);
    }
    return d;
  }

  public Matrix invert() {
    if (!isInvertible()) {
      throw new IllegalStateException("Matrix is not invertible");
    }
    Matrix m = new Matrix(numRows, numCols);
    double d = determinant();
    for (int row = 0; row < numRows(); row++) {
      for (int col = 0; col < numCols(); col++) {
        m.set(row, col, cofactor(col, row) / d);
      }
    }
    return m;
  }

  private double determinantTwoByTwo() {
    return get(0, 0) * get(1, 1) - get(1, 0) * get(0, 1);
  }

  public Matrix submatrix(int removedRow, int removedCol) {
    Matrix m = new Matrix(numRows() - 1, numCols() - 1);
    for (int row = 0; row < m.numRows(); row++) {
      for (int col = 0; col < m.numCols(); col++) {
        int fromRow = row < removedRow ? row : row + 1;
        int fromCol = col < removedCol ? col : col + 1;
        m.set(row, col, get(fromRow, fromCol));
      }
    }
    return m;
  }

  public double minor(int row, int col) {
    return submatrix(row, col).determinant();
  }

  public double cofactor(int row, int col) {
    if ((row + col) % 2 == 0) {
      return minor(row, col);
    }
    return -minor(row, col);
  }

  public boolean isInvertible() {
    return determinant() != 0;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof Matrix)) {
      return false;
    }
    Matrix other = (Matrix) obj;
    return this.numCols == other.numCols
        && this.numRows == other.numRows
        && Arrays.equals(this.values, other.values);
  }

  @Override
  public String toString() {
    StringBuffer out = new StringBuffer();
    for (int row = 0; row < numCols(); row++) {
      for (int col = 0; col < numRows(); col++) {
        out.append(get(row, col));
        out.append(", ");
      }
    }
    return out.toString();
  }
}
