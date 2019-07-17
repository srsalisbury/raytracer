package raytracer;

public class Sphere {

  private Matrix transform;

  public Sphere() {
    this.transform = Matrix.identity();
  }

  public Intersections intersect(Ray r) {
    Ray transformedRay = r.transform(transform.invert());
    Tuple sphere_to_ray = transformedRay.origin().subtract(Tuple.point(0, 0, 0));
    double a = transformedRay.direction().dot(transformedRay.direction());
    double b = 2 * transformedRay.direction().dot(sphere_to_ray);
    double c = sphere_to_ray.dot(sphere_to_ray) - 1;
    double discriminant = b * b - 4 * a * c;
    if (discriminant < 0) {
      return new Intersections();
    } else {
      Intersections intersections = new Intersections();
      intersections.add(Intersection.create((-b - Math.sqrt(discriminant)) / (2 * a), this));
      intersections.add(Intersection.create((-b + Math.sqrt(discriminant)) / (2 * a), this));
      return intersections;
    }
  }

  public Matrix getTransform() {
    return transform;
  }

  public void setTransform(Matrix transform) {
    this.transform = transform;
  }
}
