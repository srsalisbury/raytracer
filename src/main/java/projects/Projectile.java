package projects;

import com.google.auto.value.AutoValue;
import raytracer.Tuple;

@AutoValue
public abstract class Projectile {
  public static Projectile create(Tuple position, Tuple velocity) {
    return new AutoValue_Projectile(position, velocity);
  }

  public abstract Tuple position();

  public abstract Tuple velocity();

  public Projectile tick(Environment e) {
    return create(position().add(velocity()), velocity().add(e.gravity().add(e.wind())));
  }

  @Override
  public String toString() {
    return String.format("%.2f, %.2f", position().x(), position().y());
  }
}
