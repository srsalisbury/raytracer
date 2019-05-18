package raytracer;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Tuple {
  public static Tuple create(int v) {
    return new AutoValue_Tuple(v);
  }

  public abstract int v();

  public Tuple add(Tuple x) {
    return create(v() + x.v());
  }
}
