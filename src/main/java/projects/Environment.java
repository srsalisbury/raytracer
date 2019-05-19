package projects;

import com.google.auto.value.AutoValue;
import raytracer.Tuple;

@AutoValue
public abstract class Environment {
  public static Environment create(Tuple gravity, Tuple wind) {
    return new AutoValue_Environment(gravity, wind);
  }

  public abstract Tuple gravity();

  public abstract Tuple wind();
}
