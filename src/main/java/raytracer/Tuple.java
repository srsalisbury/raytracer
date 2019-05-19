package raytracer;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Tuple {
  private static final double POINT_W = 1.0;
  private static final double VECTOR_W = 0.0;

  public static Tuple point(double x, double y, double z) {
    return new AutoValue_Tuple(x, y, z, POINT_W);
  }

  public static Tuple vector(double x, double y, double z) {
    return new AutoValue_Tuple(x, y, z, VECTOR_W);
  }

  public static Tuple create(double x, double y, double z, double w) {
    return new AutoValue_Tuple(x, y, z, w);
  }

  public abstract double x();

  public abstract double y();

  public abstract double z();

  public abstract double w();

  public boolean isPoint() {
    return w() != VECTOR_W;
  }

  public boolean isVector() {
    return w() == VECTOR_W;
  }

  public Tuple add(Tuple t) {
    return create(x() + t.x(), y() + t.y(), z() + t.z(), w() + t.w());
  }

  public Tuple subtract(Tuple t) {
    return create(x() - t.x(), y() - t.y(), z() - t.z(), w() - t.w());
  }

  public Tuple times(double s) {
    return create(x() * s, y() * s, z() * s, w() * s);
  }

  public Tuple divide(double s) {
    return create(x() / s, y() / s, z() / s, w() / s);
  }

  public double magnitude() {
    return Math.sqrt(x() * x() + y() * y() + z() * z());
  }

  public Tuple normalize() {
    return divide(magnitude());
  }

  public double dot(Tuple t) {
    return (x() * t.x() + y() * t.y() + z() * t.z() + w() * t.w());
  }

  public Tuple cross(Tuple t) {
    return Tuple.vector(
        y() * t.z() - z() * t.y(), z() * t.x() - x() * t.z(), x() * t.y() - y() * t.x());
  }
}
