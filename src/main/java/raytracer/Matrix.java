package raytracer;

import java.util.Arrays;

public class Matrix {

  private int width;
  private int height;
  private double[] values;

  public Matrix(int width, int height) {
    this.width = width;
    this.height = height;
    values = new double[width * height];
  }

  public Matrix(int width, int height, double[] values) {
    this.width = width;
    this.height = height;
    this.values = new double[width * height];
    for (int i = 0; i < values.length; i++) {
      this.values[i] = values[i];
    }
  }

  public int width() {
    return width;
  }

  public int height() {
    return height;
  }

  private boolean withinRange(int row, int column) {
    return row >= 0 && row < height && column >= 0 && column < width;
  }

  private int indexOf(int row, int column) {
    return row * width + column;
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
    return this.width == other.width
        && this.height == other.height
        && Arrays.equals(this.values, other.values);
  }
}
