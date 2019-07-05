package raytracer;

public class Ray {

  private Tuple origin;
  private Tuple direction;

  public Ray(Tuple origin, Tuple direction) {
    this.origin = origin;
    this.direction = direction;
  }

  public Tuple origin() {
    return origin;
  }

  public Tuple direction() {
    return direction;
  }

  public Tuple position(double t) {
    return direction.times(t).add(origin);
  }

  public Ray transform(Matrix m) {
    return new Ray(m.times(origin), m.times(direction));
  }
}