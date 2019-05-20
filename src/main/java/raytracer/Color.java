package raytracer;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Color {
  public static final Color BLACK = Color.create(0, 0, 0);
  public static final Color WHITE = Color.create(1, 1, 1);
  public static final Color RED = Color.create(1, 0, 0);
  public static final Color GREEN = Color.create(0, 1, 0);
  public static final Color BLUE = Color.create(0, 0, 1);

  public static Color create(double r, double g, double b) {
    return new AutoValue_Color(r, g, b);
  }

  public abstract double r();

  public abstract double g();

  public abstract double b();

  public Color add(Color c) {
    return create(r() + c.r(), g() + c.g(), b() + c.b());
  }

  public Color subtract(Color c) {
    return create(r() - c.r(), g() - c.g(), b() - c.b());
  }

  public Color value(double s) {
    return create(r() * s, g() * s, b() * s);
  }

  public Color times(Color c) {
    return create(r() * c.r(), g() * c.g(), b() * c.b());
  }
}
