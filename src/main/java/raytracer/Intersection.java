package raytracer;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Intersection {

  public static Intersection create(double t, Sphere shape) {
    return new AutoValue_Intersection(t, shape);
  }

  public abstract double t();

  public abstract Sphere shape();
}
