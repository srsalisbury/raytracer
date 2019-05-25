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

  public Matrix times(Matrix m) {
    Matrix t = new Matrix(numRows(), m.numCols());
    for (int y = 0; y < numRows(); y++) {
      for (int x = 0; x < m.numCols(); x++) {
        double dot = 0.0;
        for (int i = 0; i < numCols(); i++) {
          dot += get(y, i) * m.get(i, x);
        }
        t.set(y, x, dot);
      }
    }
    return t;
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
    for (int y = 0; y < numCols(); y++) {
      for (int x = 0; x < numRows(); x++) {
        out.append(get(y, x));
        out.append(", ");
      }
    }
    return out.toString();
  }
}
