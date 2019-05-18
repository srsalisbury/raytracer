package raytracer;

public class Value {
  public int v;

  public Value(int v) {
    this.v = v;
  }

  public Value add(Value x) {
    return new Value(v + x.v);
  }
}
