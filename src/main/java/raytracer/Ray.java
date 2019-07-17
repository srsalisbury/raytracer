package raytracer;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Ray {

  public static Ray create(Tuple origin, Tuple direction) {
    return new AutoValue_Ray(origin, direction);
  }

  public abstract Tuple origin();

  public abstract Tuple direction();

  public Tuple position(double t) {
    return direction().times(t).add(origin());
  }

  public Ray transform(Matrix m) {
    return Ray.create(m.times(origin()), m.times(direction()));
  }
}
